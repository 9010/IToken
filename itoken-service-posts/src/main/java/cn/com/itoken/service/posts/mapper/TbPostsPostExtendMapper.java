package cn.com.itoken.service.posts.mapper;

import cn.com.itoken.common.service.domain.TbPostsPost;
import cn.com.itoken.common.service.tk.mybatis.mapper.MyMapper;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Primary
@Repository
public interface TbPostsPostExtendMapper extends MyMapper<TbPostsPost> {
}