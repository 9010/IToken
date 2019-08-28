package cn.com.itoken.common.service.BaseService.Impl;

import cn.com.itoken.common.service.BaseService.BaseService;
import cn.com.itoken.common.service.domain.BaseDomain;
import cn.com.itoken.common.service.tk.mybatis.mapper.MyMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class BaseServiceImpl<T extends BaseDomain, D extends MyMapper<T>> implements BaseService<T> {

    @Autowired
    private D dao;

    @Override
    public int insert(T t) {
        return dao.insert(t);
    }

    @Override
    public int delete(T t) {
        return dao.delete(t);
    }

    @Override
    public int update(T t) {
        return dao.updateByPrimaryKey(t);
    }

    @Override
    public int count(T t) {
        return dao.selectCount(t);
    }

    @Override
    public T select(T t) {
        return dao.selectOne(t);
    }

    @Override
    public PageInfo page(int pageNum, int pageSize, T t) {
        PageHelper pageHelper = new PageHelper();
        pageHelper.startPage(pageNum, pageSize);

        PageInfo<T> pageInfo = new PageInfo<>(dao.select(t));

        return pageInfo;
    }
}
