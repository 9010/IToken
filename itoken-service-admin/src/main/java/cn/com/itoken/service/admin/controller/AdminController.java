package cn.com.itoken.service.admin.controller;

import cn.com.itoken.common.dto.BaseResult;
import cn.com.itoken.common.service.domain.TbSysUser;
import cn.com.itoken.service.admin.service.AdminService;
import com.github.pagehelper.PageInfo;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "v1/admins")
public class AdminController {

    @Autowired
    private AdminService<TbSysUser> adminService;

    @RequestMapping(value = "{userCode}", method = RequestMethod.GET)
    public BaseResult get(@PathVariable(value = "userCode") String userCode){
        TbSysUser tbSysUser = new TbSysUser();
        tbSysUser.setUserCode(userCode);
        TbSysUser obj = adminService.select(tbSysUser);

        return BaseResult.ok(obj);
    }

    @RequestMapping(value = "page/{pageNum}/{pageSize}", method = RequestMethod.GET)
    public BaseResult page(
            @PathVariable(required = true) int pageNum,
            @PathVariable(required = true) int pageSize,
            @RequestParam(required = false) TbSysUser tbSysUser){
        PageInfo pageInfo = adminService.page(pageNum, pageSize, tbSysUser);

        //结果集
        List<TbSysUser> list = pageInfo.getList();

        //封装Cursor
        BaseResult.Cursor cursor = new BaseResult.Cursor();
        cursor.setTotal(new Long(pageInfo.getTotal()).intValue());
        cursor.setOffset(pageInfo.getPageNum());
        cursor.setLimit(pageInfo.getPageSize());

        return BaseResult.ok(list, cursor);
    }
}
