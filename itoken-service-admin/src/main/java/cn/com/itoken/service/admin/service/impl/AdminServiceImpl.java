package cn.com.itoken.service.admin.service.impl;

import cn.com.itoken.common.domain.TbSysUser;
import cn.com.itoken.service.admin.mapper.TbSysUserMapper;
import cn.com.itoken.service.admin.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import tk.mybatis.mapper.entity.Example;

@Service
@Transactional(readOnly = true)
public class AdminServiceImpl implements AdminService {

}
