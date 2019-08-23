package cn.com.itoken.service.sso.controller;

import cn.com.itoken.common.domain.TbSysUser;
import cn.com.itoken.service.sso.service.LoginService;
import cn.com.itoken.service.sso.service.consumer.RedisService;
import cn.com.self.itoken.common.utils.CookieUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
                        HttpServletRequest request, HttpServletResponse response,
                        Model model){
        TbSysUser tbSysUser = loginService.login(loginCode, password);

        if(tbSysUser == null) {
            model.addAttribute("message", "用户名或密码错误，请重新输入");
        }
        else {
            String token = UUID.randomUUID().toString();
            String result = redisService.put(token, loginCode, 60 * 60 * 24);
            if(request.equals("ok")){
                CookieUtils.setCookie(request, response, "token", token, 60 * 60 * 24);
                if(StringUtils.isNotBlank(url)){
                    return "redirect" + url;
                }
            }

            //熔断处理
            else {
                model.addAttribute("message", "服务器异常，请稍后再试");
            }
        }
        return "login";
    }
}
