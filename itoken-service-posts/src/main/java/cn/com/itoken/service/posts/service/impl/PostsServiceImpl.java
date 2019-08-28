package cn.com.itoken.service.posts.service.impl;

import cn.com.itoken.common.service.BaseService.Impl.BaseServiceImpl;
import cn.com.itoken.common.service.domain.BaseDomain;
import cn.com.itoken.common.service.tk.mybatis.mapper.MyMapper;
import cn.com.itoken.service.posts.service.PostsService;
import org.springframework.stereotype.Service;

@Service
public class PostsServiceImpl<T extends BaseDomain, D extends MyMapper<T>> extends BaseServiceImpl<T, D> implements PostsService<T> {
}
