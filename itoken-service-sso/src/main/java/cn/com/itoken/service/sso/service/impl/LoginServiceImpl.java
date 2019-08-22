package cn.com.itoken.service.sso.service.impl;

import cn.com.itoken.common.domain.TbSysUser;
import cn.com.itoken.service.sso.mapper.TbSysUserMapper;
import cn.com.itoken.service.sso.service.LoginService;
import cn.com.itoken.service.sso.service.consumer.RedisService;
import cn.com.self.itoken.common.utils.MapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import tk.mybatis.mapper.entity.Example;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private RedisService redisService;

    @Autowired
    private TbSysUserMapper tbSysUserMapper;

    @Override
    public TbSysUser login(String loginCode, String plantPassword) {
        TbSysUser tbSysUser = null;

        String json = redisService.get(loginCode);

        //用户信息不存在redis中
        if(json == null){
            Example example = new Example(TbSysUser.class);
            example.createCriteria().andEqualTo("loginCode", loginCode);

            tbSysUser = tbSysUserMapper.selectOneByExample(example);
            String checkPassword = DigestUtils.md5DigestAsHex(plantPassword.getBytes());
            if(checkPassword.equals(tbSysUser.getPassword())){
                try {
                    redisService.put(loginCode, MapperUtils.obj2json(tbSysUser), 60 * 60 * 24);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return tbSysUser;
            }
            else {
                return null;
            }
        }
        //redis中存在
        else {
            try {
                tbSysUser = MapperUtils.json2pojo(json, TbSysUser.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return tbSysUser;
    }
}
