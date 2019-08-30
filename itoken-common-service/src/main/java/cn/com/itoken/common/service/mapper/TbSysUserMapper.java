package cn.com.itoken.common.service.mapper;

import cn.com.itoken.common.service.domain.TbSysUser;
import cn.com.itoken.common.service.tk.mybatis.mapper.MyMapper;
import cn.com.itoken.common.service.utils.RedisCache;
import org.apache.ibatis.annotations.CacheNamespace;

@CacheNamespace(implementation = RedisCache.class)
public interface TbSysUserMapper extends MyMapper<TbSysUser> {
}