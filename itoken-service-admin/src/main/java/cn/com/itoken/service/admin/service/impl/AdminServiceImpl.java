package cn.com.itoken.service.admin.service.impl;

import cn.com.itoken.service.admin.mapper.TbSysUserMapper;
import cn.com.itoken.service.admin.service.AdminService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class AdminServiceImpl implements AdminService {

}
