package com.kodemamba.wxshop.config;

import com.kodemamba.wxshop.service.VerificationCodeCacheService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author shengding
 * @Date 2020/8/21 22:18
 * @Description
 */
public class ShiroRealm extends AuthorizingRealm {
    private final VerificationCodeCacheService verificationCodeCacheService;

    @Autowired
    public ShiroRealm(VerificationCodeCacheService verificationCodeCacheService) {
        this.verificationCodeCacheService = verificationCodeCacheService;
        this.setCredentialsMatcher((token, info) -> new String((char[]) token.getCredentials()).equals(info.getCredentials()));
    }

    /**
     * 验证本人是否具有权限
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    /**
     * 验证是否是本人
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String tel = (String) authenticationToken.getPrincipal();
        String correctCode = verificationCodeCacheService.getCorrectCode(tel);
        return new SimpleAuthenticationInfo(tel, correctCode, getName());
    }
}

