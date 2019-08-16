package cn.com.itoken.service.admin.service.impl;

import cn.com.itoken.service.admin.domain.TbSysUser;
import cn.com.itoken.service.admin.mapper.TbSysUserMapper;
import cn.com.itoken.service.admin.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private TbSysUserMapper tbSysUserMapper;

    @Override
    public void register(TbSysUser tbSysUser) {
        tbSysUserMapper.insert(tbSysUser);
    }

    @Override
    public TbSysUser login(String account, String password) {
        return null;
    }
}
