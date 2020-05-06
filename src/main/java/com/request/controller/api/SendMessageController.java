package com.request.controller.api;
import com.request.service.LoginService;
import com.request.vo.User;
import com.request.vo.result.RestResult;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.springframework.http.HttpStatus.OK;

@RestController
public class SendMessageController {

    @Resource
    LoginService loginService;

//    @RequiresRoles("admin")
    @RequiresPermissions("add")
    @RequestMapping(value = "/shreeRequest", method = {RequestMethod.GET, RequestMethod.POST})
    public Map sendMessage() {
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "test message, hello!";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String,Object> map=new HashMap<>();
        map.put("messageId",messageId);
        map.put("messageData",messageData);
        map.put("createTime",createTime);
        return map;
    }

    @RequestMapping(value = "/getUserByName", method = {RequestMethod.GET, RequestMethod.POST})
    public RestResult getUserByName(@RequestParam String userName) {
        RestResult restResult = RestResult.createSuccessResult("获取成功！");
        User user = loginService.getUserByName(userName);
        if(user==null){
            restResult.setErrorResult("无用户信息！");
        }else{
            restResult.setData(user);
        }
        return restResult;
    }
}
