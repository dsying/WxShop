package com.kodemamba.wxshop.service;

import org.springframework.stereotype.Service;

/**
 * @Author shengding
 * @Date 2020/8/20 21:47
 * @Description
 */
@Service
public class MockSmsCodeService implements SmsCodeService{
    @Override
    public String sendSmsCode(String tel) {
        return "0000";
    }
}
