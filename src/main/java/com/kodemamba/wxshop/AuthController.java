package com.kodemamba.wxshop;

import com.kodemamba.wxshop.service.AuthService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author shengding
 * @Date 2020/8/20 21:52
 * @Description
 */
@RestController
@RequestMapping("/api")
public class AuthController {
    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    /**
     * 发送验证码
     * @param codeInfo
     */
    @PostMapping("/code")
    public void code(@RequestBody CodeInfo codeInfo) {
        authService.sendVerificationCode(codeInfo.getTel());
    }

    /**
     * 登录
     * @param codeInfo
     */
    @PostMapping("/login")
    public void login(@RequestBody CodeInfo codeInfo) {
        String tel = codeInfo.getTel();
        String code = codeInfo.getCode();
        UsernamePasswordToken token = new UsernamePasswordToken(tel, code);
        token.setRememberMe(true);
        try {
            SecurityUtils.getSubject().login(token);
            System.out.println("登录成功!");
        } catch (AuthenticationException e) {
            e.printStackTrace();
            System.out.println("登录失败!");
        }
    }
}
