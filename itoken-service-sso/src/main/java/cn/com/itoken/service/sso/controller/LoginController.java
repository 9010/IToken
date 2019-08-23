package cn.com.itoken.service.sso.controller;

import cn.com.itoken.common.domain.TbSysUser;
import cn.com.itoken.service.sso.service.LoginService;
import cn.com.itoken.service.sso.service.consumer.RedisService;
import cn.com.self.itoken.common.utils.CookieUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(String loginCode, String password, @RequestParam(required = false) String url,
                        HttpServletRequest request, HttpServletResponse response){
        TbSysUser tbSysUser = loginService.login(loginCode, password);

        if(tbSysUser != null){
            String token = UUID.randomUUID().toString();
            String result = redisService.put(token, loginCode, 60 * 60 * 24);
            if(request.equals("ok")){
                CookieUtils.setCookie(request, response, "token", token);
                return "redirect" + url;
            }

        }

        return "login";
    }
}
