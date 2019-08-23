package cn.com.itoken.service.sso.controller;

import cn.com.itoken.common.domain.TbSysUser;
import cn.com.itoken.service.sso.service.LoginService;
import cn.com.itoken.service.sso.service.consumer.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.UUID;

@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private RedisService redisService;

    /**
     * 跳转登录页面
     * @return
     */
    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String login(){
        return "login";
    }

    /**
     * 登录业务
     * @param loginCode
     * @param password
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(String loginCode, String password){
        TbSysUser tbSysUser = loginService.login(loginCode, password);

        if(tbSysUser != null){
            String token = UUID.randomUUID().toString();
            redisService.put("token", token, 60 * 60 * 24);
        }

        System.out.println(tbSysUser);
        return "ok";
    }
}
