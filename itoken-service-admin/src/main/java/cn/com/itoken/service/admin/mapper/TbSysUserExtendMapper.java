package cn.com.itoken.service.admin.mapper;

import cn.com.itoken.common.service.domain.TbSysUser;
import cn.com.itoken.common.service.tk.mybatis.mapper.MyMapper;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Primary
@Repository
public interface TbSysUserExtendMapper extends MyMapper<TbSysUser> {
}