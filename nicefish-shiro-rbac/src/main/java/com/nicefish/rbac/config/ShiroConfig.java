package com.nicefish.rbac.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.nicefish.rbac.shiro.filter.NiceFishCaptchaValidateFilter;
import com.nicefish.rbac.shiro.filter.NiceFishLogoutFilter;
import com.nicefish.rbac.shiro.filter.NiceFishUserFilter;
import com.nicefish.rbac.shiro.realm.NiceFishRbacRealm;
import com.nicefish.rbac.shiro.session.NiceFishMySQLSessionDAO;
import com.nicefish.rbac.shiro.session.NiceFishSessionFactory;

import org.apache.shiro.cache.ehcache.EhCacheManager;
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
    @Value("${shiro.session.timeout}")//单位：小时
    private int timeout;

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
     * Shiro 的过滤器配置。
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

    /**
     * 创建自定义的 NiceFishRbacRealm 实例。
     * @return
     */
    @Bean
    public NiceFishRbacRealm nicefishRbacRealm() {
        NiceFishRbacRealm niceFishRbacRealm = new NiceFishRbacRealm();
        niceFishRbacRealm.setCachingEnabled(true);
        niceFishRbacRealm.setAuthenticationCachingEnabled(true);
        niceFishRbacRealm.setAuthenticationCacheName("authenticationCache");
        niceFishRbacRealm.setAuthorizationCachingEnabled(true);
        niceFishRbacRealm.setAuthorizationCacheName("authorizationCache");
        return niceFishRbacRealm;
    }

    /**
     * 创建自定义的 NiceFishMySQLSessionDAO 实例
     * @return
     */
    @Bean
    public NiceFishMySQLSessionDAO sessionDAO() {
        NiceFishMySQLSessionDAO sessionDAO = new NiceFishMySQLSessionDAO();
        sessionDAO.setActiveSessionsCacheName("shiro-activeSessionCache");
        return sessionDAO;
    }

    /**
     * 创建自定义的 NiceFishSessionFactory 实例
     * @return
     */
    @Bean
    public NiceFishSessionFactory sessionFactory() {
        NiceFishSessionFactory sessionFactory = new NiceFishSessionFactory();
        return sessionFactory;
    }

    /**
     * 创建 EhCache 实例
     */
    @Bean
    public EhCacheManager ehCacheManager(){
        EhCacheManager cacheManager = new EhCacheManager();
        cacheManager.setCacheManagerConfigFile("classpath:ehcache-shiro.xml");
        return cacheManager;
    }

    /**
     * web 应用和 spring 本身没有 SessionDAO 的概念，
     * 这里创建 Shiro 提供的 DefaultWebSessionManager 实例，从而可以自定义 Session 的数据格式，
     * 然后利用自定义的 NiceFishMySQLSessionDAO 对 Session 进行操作。
     * @see https://shiro.apache.org/session-management.html
     * @return
     */
    @Bean
    public DefaultWebSessionManager sessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        
        //启用 EhCache 缓存，Shiro 默认不启用
        //启用 EhCache 缓存之后，需要在持久化的 Session 和缓存中的 Session 之间进行数据同步。
        //EhCache 实例配置位于 classpath:ehcache-shiro.xml 文件中，session 默认缓存在 "shiro-activeSessionCache" 实例中。
        //认证、授权、Session，全部使用同一个 EhCache 运行时对象。
        sessionManager.setCacheManager(ehCacheManager());

        sessionManager.setDeleteInvalidSessions(true);
        sessionManager.setGlobalSessionTimeout(timeout * 60 * 60 *1000);
        sessionManager.setSessionIdUrlRewritingEnabled(false);
        
        //启用定时调度器，用来清理 Session ，Shiro 默认采用内置的 ExecutorServiceSessionValidationScheduler 进行调度。
        sessionManager.setSessionValidationSchedulerEnabled(true);
        
        sessionManager.setSessionDAO(sessionDAO());
        sessionManager.setSessionFactory(sessionFactory());
        return sessionManager;
    }

    @Bean
    public SecurityManager securityManager(){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(nicefishRbacRealm());
        securityManager.setRememberMeManager(rememberMeManager());
        
        //启用自定义的 SessionManager
        securityManager.setSessionManager(sessionManager());

        //启用缓存管理器，用来缓存认证、授权信息。
        //EhCache 实例配置位于 classpath:ehcache-shiro.xml 文件中
        //认证信息默认操作 "authenticationCache" ，授权信息默认操作 "authorizationCache" 。
        //认证、授权、Session，全部使用同一个 EhCache 运行时对象。
        securityManager.setCacheManager(ehCacheManager());
        
        return securityManager;
    }

    public NiceFishCaptchaValidateFilter captchaValidateFilter() {
        NiceFishCaptchaValidateFilter niceFishCaptchaValidateFilter = new NiceFishCaptchaValidateFilter();
        niceFishCaptchaValidateFilter.setCaptchaEnabled(captchaEnabled);
        niceFishCaptchaValidateFilter.setCaptchaType(captchaType);
        return niceFishCaptchaValidateFilter;
    }

    public NiceFishLogoutFilter logoutFilter(){
        NiceFishLogoutFilter niceFishLogoutFilter =new NiceFishLogoutFilter();
        return niceFishLogoutFilter;
    }

    public NiceFishUserFilter userFilter(){
        NiceFishUserFilter niceFishUserFilter =new NiceFishUserFilter();
        return niceFishUserFilter;
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
