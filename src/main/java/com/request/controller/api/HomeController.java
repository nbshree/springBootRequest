package com.request.controller.api;

import com.request.service.LoginService;
import com.request.vo.result.RestResult;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class HomeController {
    @Resource
    LoginService loginService;
    @RequestMapping(value = "/login", method = {RequestMethod.GET, RequestMethod.POST})
    public RestResult login(@RequestParam String userName, @RequestParam String userPwd) {
            // Shiro登录
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(userName, userPwd);
            try {
                //进行验证，这里可以捕获异常，然后返回对应信息
                subject.login(token);
//            subject.checkRole("admin");
//            subject.checkPermissions("query", "add");
            } catch (AuthenticationException e) {
                e.printStackTrace();
                return RestResult.createFailedResult("账号或密码错误！");
            } catch (AuthorizationException e) {
                e.printStackTrace();
                return RestResult.createFailedResult("没有权限");
            }
            return RestResult.createSuccessResult("200", loginService.getUserByName(userName),subject.getSession().getId().toString());
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
