package cn.com.itoken.service.posts.service.impl;

import cn.com.itoken.common.service.BaseService.Impl.BaseServiceImpl;
import cn.com.itoken.common.service.domain.TbPostsPost;
import cn.com.itoken.common.service.mapper.TbPostsPostMapper;
import cn.com.itoken.service.posts.service.PostsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class PostsServiceImpl extends BaseServiceImpl<TbPostsPost, TbPostsPostMapper> implements PostsService<TbPostsPost> {
}
