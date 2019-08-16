package cn.com.itoken.service.admin.service.impl;

import cn.com.itoken.service.admin.domain.TbSysUser;
import cn.com.itoken.service.admin.mapper.TbSysUserMapper;
import cn.com.itoken.service.admin.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import tk.mybatis.mapper.entity.Example;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private TbSysUserMapper tbSysUserMapper;

    @Override
    public void register(TbSysUser tbSysUser) {
        tbSysUser.setPassword(DigestUtils.md5DigestAsHex(tbSysUser.getPassword().getBytes()));  //MD5加密
        tbSysUserMapper.insert(tbSysUser);
    }

    @Override
    public TbSysUser login(String account, String password) {
        Example example = new Example(TbSysUser.class);
        example.createCriteria().andEqualTo("loginCode", account);

        TbSysUser tbSysUser = tbSysUserMapper.selectOneByExample(example);
        String checkPassword = DigestUtils.md5DigestAsHex(password.getBytes());
        if(checkPassword.equals(tbSysUser.getPassword())){
            return tbSysUser;
        }
        return null;
    }
}
