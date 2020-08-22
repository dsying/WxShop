package com.kodemamba.wxshop;

import com.kodemamba.wxshop.service.AuthService;
import com.kodemamba.wxshop.service.TelVerificationService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * @Author shengding
 * @Date 2020/8/20 21:52
 * @Description
 */
@RestController
@RequestMapping("/api")
public class AuthController {
    private final AuthService authService;
    private final TelVerificationService telVerificationService;

    @Autowired
    public AuthController(AuthService authService, TelVerificationService telVerificationService) {
        this.authService = authService;
        this.telVerificationService = telVerificationService;
    }

    /**
     * 发送验证码
     * @param codeInfo
     * @param response
     */
    @PostMapping("/code")
    public void code(@RequestBody CodeInfo codeInfo, HttpServletResponse response) {
        String tel = codeInfo.getTel();
        if (telVerificationService.verifyTelParameter(tel)) {
            authService.sendVerificationCode(tel);
        } else {
           response.setStatus(HttpStatus.BAD_REQUEST.value());
        }
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
