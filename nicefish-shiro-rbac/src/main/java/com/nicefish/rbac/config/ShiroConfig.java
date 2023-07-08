package com.nicefish.rbac.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.nicefish.rbac.shiro.filter.FishCaptchaValidateFilter;
import com.nicefish.rbac.shiro.filter.FishLogoutFilter;
import com.nicefish.rbac.shiro.filter.FishUserFilter;
import com.nicefish.rbac.shiro.realm.FishUserRealm;
import com.nicefish.rbac.shiro.session.FishRedisSessionDAO;
import com.nicefish.rbac.shiro.session.FishSessionFactory;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author 大漠穷秋
 */
@Configuration
public class ShiroConfig {
    @Value("${shiro.session.expireTime}")//单位：小时
    private int expireTime;

    @Value("${shiro.session.validationInterval}")
    private int validationInterval;

    @Value("${shiro.session.maxSession}")
    private int maxSession;

    @Value("${shiro.session.kickoutAfter}")
    private boolean kickoutAfter;

    @Value("${shiro.user.captchaEnabled}")
    private boolean captchaEnabled;

    @Value("${shiro.user.captchaType}")
    private String captchaType;

    @Value("${shiro.cookie.domain}")
    private String domain;

    @Value("${shiro.cookie.path}")
    private String path;

    @Value("${shiro.cookie.httpOnly}")
    private boolean httpOnly;

    @Value("${shiro.cookie.maxAge}")
    private int maxAge;

    @Value("${shiro.user.loginUrl}")
    private String loginUrl;

    @Value("${shiro.user.unauthorizedUrl}")
    private String unauthorizedUrl;

    /**
     * Shiro 的核心配置
     * @see <a href="https://shiro.apache.org/documentation.html">Shiro Docs</a>
     * @param securityManager
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        shiroFilterFactoryBean.setLoginUrl(loginUrl);
        shiroFilterFactoryBean.setUnauthorizedUrl(unauthorizedUrl);

        Map<String, Filter> filters = new LinkedHashMap<String, Filter>();
        filters.put("captchaValidate", captchaValidateFilter());
        filters.put("user", userFilter());
        filters.put("fishLogoutFilter",logoutFilter());
        shiroFilterFactoryBean.setFilters(filters);

        //TODO:所有静态资源交给Nginx管理，删掉这里关于静态资源的配置
        LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        filterChainDefinitionMap.put("/**.ico**", "anon");
        filterChainDefinitionMap.put("/**.png**", "anon");
        filterChainDefinitionMap.put("/**.jpg**", "anon");
        filterChainDefinitionMap.put("/**.jpeg**", "anon");
        filterChainDefinitionMap.put("/css/**", "anon");
        filterChainDefinitionMap.put("/docs/**", "anon");
        filterChainDefinitionMap.put("/fonts/**", "anon");
        filterChainDefinitionMap.put("/img/**", "anon");
        filterChainDefinitionMap.put("/ajax/**", "anon");
        filterChainDefinitionMap.put("/js/**", "anon");
        filterChainDefinitionMap.put("/druid/**", "anon");
        filterChainDefinitionMap.put("/swagger-ui.html", "anon");
        filterChainDefinitionMap.put("/swagger-resources", "anon");
        filterChainDefinitionMap.put("/nicefish/auth/captcha/captchaImage**", "anon");
        filterChainDefinitionMap.put("/nicefish/auth/shiro/logout", "fishLogoutFilter");

        //需要加验证码保护的接口
        //TODO:前端需要改成动态显示验证码的方式，默认不显示验证码，当访问次数过于频繁的时候，才显示验证码。
        filterChainDefinitionMap.put("/nicefish/cms/post/write-post", "captchaValidate");
        filterChainDefinitionMap.put("/nicefish/cms/post/edit-post", "captchaValidate");
        filterChainDefinitionMap.put("/nicefish/cms/comment/write-comment", "captchaValidate");
        filterChainDefinitionMap.put("/nicefish/auth/user/register", "anon,captchaValidate");
        filterChainDefinitionMap.put("/nicefish/auth/shiro/login", "anon,captchaValidate");

        //其它所有请求全部交给userFilter过滤
        filterChainDefinitionMap.put("/*", "user");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        return shiroFilterFactoryBean;
    }

    @Bean
    public FishUserRealm userRealm() {
        FishUserRealm fishUserRealm = new FishUserRealm();
        return fishUserRealm;
    }

    //这里的方法名称 sessionDAO 不能改，在其它地方有 Autowired
    @Bean
    public FishRedisSessionDAO sessionDAO() {
        FishRedisSessionDAO sessionDAO = new FishRedisSessionDAO();
        return sessionDAO;
    }

    @Bean
    public FishSessionFactory sessionFactory() {
        FishSessionFactory sessionFactory = new FishSessionFactory();
        return sessionFactory;
    }

    @Bean
    public DefaultWebSessionManager sessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setDeleteInvalidSessions(true);
        sessionManager.setGlobalSessionTimeout(expireTime * 60 * 60 *1000);
        sessionManager.setSessionIdUrlRewritingEnabled(false);
        sessionManager.setSessionValidationSchedulerEnabled(true);
        sessionManager.setSessionDAO(sessionDAO());
        sessionManager.setSessionFactory(sessionFactory());
        return sessionManager;
    }

    @Bean
    public SecurityManager securityManager(){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm());
        securityManager.setRememberMeManager(rememberMeManager());
        securityManager.setSessionManager(sessionManager());
        return securityManager;
    }

    public FishCaptchaValidateFilter captchaValidateFilter() {
        FishCaptchaValidateFilter fishCaptchaValidateFilter = new FishCaptchaValidateFilter();
        fishCaptchaValidateFilter.setCaptchaEnabled(captchaEnabled);
        fishCaptchaValidateFilter.setCaptchaType(captchaType);
        return fishCaptchaValidateFilter;
    }

    public FishLogoutFilter logoutFilter(){
        FishLogoutFilter fishLogoutFilter=new FishLogoutFilter();
        return fishLogoutFilter;
    }

    public FishUserFilter userFilter(){
        FishUserFilter fishUserFilter=new FishUserFilter();
        return fishUserFilter;
    }

    public SimpleCookie rememberMeCookie() {
        SimpleCookie cookie = new SimpleCookie("rememberMe");
        cookie.setDomain(domain);
        cookie.setPath(path);
        cookie.setHttpOnly(httpOnly);
        cookie.setMaxAge(maxAge * 60 * 60 *1000);
        return cookie;
    }

    public CookieRememberMeManager rememberMeManager() {
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        cookieRememberMeManager.setCipherKey(Base64.decode("fCq+/xW488hMTCD+cmJ3aQ=="));
        return cookieRememberMeManager;
    }

    @Bean
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(@Qualifier("securityManager") SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
}
