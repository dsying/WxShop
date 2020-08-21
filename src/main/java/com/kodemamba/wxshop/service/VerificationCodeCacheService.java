package com.kodemamba.wxshop.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author shengding
 * @Date 2020/8/20 22:58
 * @Description
 */
@Service
public class VerificationCodeCacheService {
    private Map<String, String> telNumToCode = new ConcurrentHashMap<>();

    public void addCode(String tel, String smsCode) {
        telNumToCode.put(tel, smsCode);
    }

    public String getCorrectCode(String tel) {
        return telNumToCode.get(tel);
    }
}
