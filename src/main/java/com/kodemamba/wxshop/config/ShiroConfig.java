package com.kodemamba.wxshop.config;

import com.kodemamba.wxshop.service.VerificationCodeCacheService;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author shengding
 * @Date 2020/8/20 21:29
 * @Description
 */
@Configuration
public class ShiroConfig {
    @Autowired
    VerificationCodeCacheService verificationCodeCacheService;

    @Bean
    ShiroRealm shiroRealm() {
        return new ShiroRealm(verificationCodeCacheService);
    }

    @Bean
    SecurityManager securityManager() {
        DefaultSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(shiroRealm());
        securityManager.setCacheManager(new MemoryConstrainedCacheManager());
        securityManager.setSessionManager(new DefaultWebSessionManager());
        return securityManager;
    }

    @Bean
    ShiroFilterFactoryBean shiroFilterFactoryBean() {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(securityManager());

        Map<String, String> map = new LinkedHashMap<>();
        map.put("/api/code", "anon");
        map.put("/api/login", "anon");
        bean.setFilterChainDefinitionMap(map);
        return bean;
    }


}
