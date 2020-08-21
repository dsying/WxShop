package com.kodemamba.wxshop.service;

/**
 * @Author shengding
 * @Date 2020/8/20 21:45
 * @Description
 */
public interface SmsCodeService {
    /**
     * 向指定手机发送验证码，返回正确答案
     * @param tel 目标手机号
     * @return 正确答案
     */
    String sendSmsCode(String tel);
}
