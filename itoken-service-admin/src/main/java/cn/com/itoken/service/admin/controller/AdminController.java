package cn.com.itoken.service.admin.controller;

import cn.com.itoken.service.admin.domain.TbSysUser;
import cn.com.itoken.service.admin.service.AdminService;
import cn.com.self.itoken.common.dto.BaseResult;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AdminController {

    @Autowired
    private AdminService adminService;

    /**
     * 登录
     * @param loginCode
     * @param password
     * @return
     */
    @RequestMapping(value = "login", method = RequestMethod.GET)
    public BaseResult login(String loginCode, String password){
        TbSysUser tbSysUser = adminService.login(loginCode, password);

        if(tbSysUser != null){  //登录成功
            return BaseResult.ok(tbSysUser);
        }
        else {  //登录失败
            return BaseResult.notOk();
        }
    }

    private BaseResult checkLogin(String loginCode, String password){
        BaseResult baseResult = null;
        List<BaseResult.Error> errors = new ArrayList<>();

        if(StringUtils.isBlank(loginCode)){
            BaseResult.Error error = new BaseResult.Error();
            error.setField("loginCode");
            error.setMessage("登录账号不能为空");
            errors.add(error);
        }

        if(StringUtils.isBlank(password)){
            BaseResult.Error error = new BaseResult.Error();
            error.setField("password");
            error.setMessage("登录密码不能为空");
            errors.add(error);
        }

        return baseResult;
    }
}
