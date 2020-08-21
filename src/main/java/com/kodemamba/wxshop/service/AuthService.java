package com.kodemamba.wxshop.service;

import com.kodemamba.wxshop.generate.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserService userService;
    private final SmsCodeService smsCodeService;
    private final VerificationCodeCacheService verificationCodeCacheService;

    @Autowired
    public AuthService(UserService userService,
                       SmsCodeService smsCodeService,
                       VerificationCodeCacheService verificationCodeCacheService) {
        this.userService = userService;
        this.smsCodeService = smsCodeService;
        this.verificationCodeCacheService = verificationCodeCacheService;
    }

    public User sendVerificationCode(String tel) {
        User user = userService.createUserIfNotExists(tel);
        String smsCode = smsCodeService.sendSmsCode(tel);
        verificationCodeCacheService.addCode(tel, smsCode);
        return user;
    }
}
