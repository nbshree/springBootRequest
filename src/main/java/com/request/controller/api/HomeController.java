package com.request.controller.api;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;

@RestController
public class HomeController {

    @RequestMapping(value = "/login", method = {RequestMethod.GET, RequestMethod.POST})
    public String login(@RequestParam String userName, @RequestParam String userPwd) {
        try {
            // Shiro登录
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(userName, userPwd);
            subject.login(token);
            subject.getSession().setAttribute("user", userName);
            return "登陆成功";
        } catch (Exception ex) {
            return "登陆失败" + ex;
        }
    }

    @RequestMapping(value = "/logoff", method = {RequestMethod.GET, RequestMethod.POST})
    public String logoff() {
        try {
            // Shiro登录
            Subject subject = SecurityUtils.getSubject();
            subject.logout();
            return "退出成功";
        } catch (Exception ex) {
            return "退出失败" + ex;
        }
    }
}
