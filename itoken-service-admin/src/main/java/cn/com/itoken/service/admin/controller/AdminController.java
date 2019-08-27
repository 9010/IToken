package cn.com.itoken.service.admin.controller;

import cn.com.itoken.common.domain.TbSysUser;
import cn.com.itoken.service.admin.service.AdminService;
import cn.com.self.itoken.common.dto.BaseResult;
import com.google.common.collect.Lists;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

    @Autowired
    private AdminService adminService;

}
