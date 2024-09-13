package com.nicefish.rbac.config;

import com.nicefish.rbac.shiro.filter.NiceFishCaptchaValidateFilter;
import com.nicefish.rbac.shiro.realm.NiceFishMySQLRealm;
import com.nicefish.rbac.shiro.session.NiceFishSessionDAO;
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

import jakarta.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
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
        filters.put("captchaValidateFilter", captchaValidateFilter());
        shiroFilterFactoryBean.setFilters(filters);

        //所有静态资源交给Nginx管理，这里只配置与 shiro 相关的过滤器。
        LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        filterChainDefinitionMap.put("/nicefish/cms/post/write-post", "captchaValidateFilter");
        filterChainDefinitionMap.put("/nicefish/cms/post/update-post", "captchaValidateFilter");
        filterChainDefinitionMap.put("/nicefish/cms/comment/write-comment", "captchaValidateFilter");
        filterChainDefinitionMap.put("/nicefish/auth/user/register", "anon,captchaValidateFilter");
        filterChainDefinitionMap.put("/nicefish/auth/shiro/login", "anon,captchaValidateFilter");
        filterChainDefinitionMap.put("/**", "anon");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    /**
     * 创建自定义的 NiceFishMySQLRealm 实例。
     * @return
     */
    @Bean
    public NiceFishMySQLRealm nicefishRbacRealm() {
        NiceFishMySQLRealm niceFishMySQLRealm = new NiceFishMySQLRealm();
        niceFishMySQLRealm.setCachingEnabled(true);
        niceFishMySQLRealm.setAuthenticationCachingEnabled(true);
        niceFishMySQLRealm.setAuthenticationCacheName("authenticationCache");
        niceFishMySQLRealm.setAuthorizationCachingEnabled(true);
        niceFishMySQLRealm.setAuthorizationCacheName("authorizationCache");
        return niceFishMySQLRealm;
    }

    /**
     * 创建自定义的 NiceFishSessionDAO 实例
     * @return
     */
    @Bean
    public NiceFishSessionDAO sessionDAO() {
        NiceFishSessionDAO nfSessionDAO = new NiceFishSessionDAO();
        nfSessionDAO.setActiveSessionsCacheName("shiro-activeSessionCache");
        return nfSessionDAO;
    }

    /**
     * 创建自定义的 NiceFishSessionFactory 实例
     * @return NiceFishSessionFactory
     */
    @Bean
    public NiceFishSessionFactory sessionFactory() {
        NiceFishSessionFactory nfSessionFactory = new NiceFishSessionFactory();
        return nfSessionFactory;
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
     * 创建 DefaultWebSessionManager 实例，设置默认 SessionDAO 和 SimpleSessionFactory 。
     * @return DefaultWebSessionManager
     */
    @Bean
    public DefaultWebSessionManager sessionManager() {
        DefaultWebSessionManager defaultWebSessionMgr = new DefaultWebSessionManager();
        
        //启用 EhCache 缓存，Shiro 默认不启用
        //启用 EhCache 缓存之后，需要在持久化的 Session 和缓存中的 Session 之间进行数据同步。
        //EhCache 实例配置位于 classpath:ehcache-shiro.xml 文件中，session 默认缓存在 "shiro-activeSessionCache" 实例中。
        //认证、授权、Session，全部使用同一个 EhCache 运行时对象。
        defaultWebSessionMgr.setCacheManager(ehCacheManager());

        defaultWebSessionMgr.setDeleteInvalidSessions(true);
        defaultWebSessionMgr.setGlobalSessionTimeout(timeout);
        defaultWebSessionMgr.setSessionIdUrlRewritingEnabled(false);
        
        //启用定时调度器，用来清理 Session ，Shiro 默认采用内置的 ExecutorServiceSessionValidationScheduler 进行调度。
        defaultWebSessionMgr.setSessionValidationSchedulerEnabled(true);
        defaultWebSessionMgr.setSessionValidationInterval(validationInterval);

        defaultWebSessionMgr.setSessionDAO(sessionDAO());
        defaultWebSessionMgr.setSessionFactory(sessionFactory());
        return defaultWebSessionMgr;
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

    public SimpleCookie rememberMeCookie() {
        SimpleCookie cookie = new SimpleCookie("rememberMe");
        cookie.setDomain(domain);
        cookie.setPath(path);
        cookie.setHttpOnly(httpOnly);
        cookie.setMaxAge(maxAge);
        return cookie;
    }

    public CookieRememberMeManager rememberMeManager() {
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        cookieRememberMeManager.setCipherKey(Base64.decode("fCq+/xW488hMTCD+cmJ3aQ=="));//FIXME:Generate Cipher Key
        return cookieRememberMeManager;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(
            @Qualifier("securityManager") SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
}
