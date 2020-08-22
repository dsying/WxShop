package com.kodemamba.wxshop.service;

import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

/**
 * @Author shengding
 * @Date 2020/8/22 20:15
 * @Description
 */
@Service
public class TelVerificationService {
    private static final Pattern telPattern = Pattern.compile("^(1[3-9]([0-9]{9}))$");

    public boolean verifyTelParameter(String tel) {
        if (tel == null) {
            return false;
        } else  {
            return telPattern.matcher(tel).find();
        }
    }
}
