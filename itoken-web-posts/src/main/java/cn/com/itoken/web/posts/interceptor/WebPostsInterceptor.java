package cn.com.itoken.web.posts.interceptor;

import cn.com.itoken.common.service.domain.TbSysUser;
import cn.com.itoken.common.utils.CookieUtils;
import cn.com.itoken.common.utils.MapperUtils;
import cn.com.itoken.common.web.constants.WebConstants;
import cn.com.itoken.common.web.utils.HttpServletUtils;
import cn.com.itoken.web.posts.service.RedisService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class WebPostsInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisService redisService;

    @Value(value = "${hosts.sso}")
    private String hosts_sso;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = CookieUtils.getCookieValue(request, WebConstants.SESSION_TOKEN);
        //一定没有登录
        if(StringUtils.isBlank(token)){
            try {
                //http://localhost:8503/login?url=http://localhost:8601
                response.sendRedirect(String.format("%s/login?url=%s", hosts_sso, HttpServletUtils.getFullPath(request)));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HttpSession session = request.getSession();
        TbSysUser tbSysUser = (TbSysUser) session.getAttribute(WebConstants.SESSION_USER);

        //已登录
        if(tbSysUser != null){
            if(modelAndView != null){
                modelAndView.addObject(WebConstants.SESSION_USER, tbSysUser);
            }
        }

        //未登录
        else {
            String token = CookieUtils.getCookieValue(request, WebConstants.SESSION_TOKEN);
            if(StringUtils.isNotBlank(token)){
                String loginCode = redisService.get(token);
                if(StringUtils.isNotBlank(loginCode)){
                    String json = redisService.get(loginCode);
                    if(!json.equals("not_ok")){
                        try {
                            //已登录，创建局部会话
                            tbSysUser = MapperUtils.json2pojo(json, TbSysUser.class);
                            if(modelAndView != null){
                                modelAndView.addObject(WebConstants.SESSION_USER, tbSysUser);
                            }
                            session.setAttribute(WebConstants.SESSION_USER, tbSysUser);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }

        //二次确认是否存在登录信息，不存在则要求用户重新登录
        if(tbSysUser == null){
            response.sendRedirect(String.format("%s/login?url=%s", hosts_sso, HttpServletUtils.getFullPath(request)));
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
