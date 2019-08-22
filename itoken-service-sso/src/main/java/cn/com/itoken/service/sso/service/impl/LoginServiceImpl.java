package cn.com.itoken.service.sso.service.impl;

import cn.com.itoken.common.domain.TbSysUser;
import cn.com.itoken.service.sso.mapper.TbSysUserMapper;
import cn.com.itoken.service.sso.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import tk.mybatis.mapper.entity.Example;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private TbSysUserMapper tbSysUserMapper;

    @Override
    public TbSysUser login(String loginCode, String plantPassword) {
        Example example = new Example(TbSysUser.class);
        example.createCriteria().andEqualTo("loginCode", loginCode);

        TbSysUser tbSysUser = tbSysUserMapper.selectOneByExample(example);
        String checkPassword = DigestUtils.md5DigestAsHex(plantPassword.getBytes());
        if(checkPassword.equals(tbSysUser.getPassword())){
            return tbSysUser;
        }
        return null;
    }
}
