package cn.com.itoken.common.service.BaseService;

import cn.com.itoken.common.service.domain.BaseDomain;
import com.github.pagehelper.PageInfo;

public interface BaseService <T extends BaseDomain> {
    public int insert(T t);

    public int delete(T t);

    public int update(T t);

    public int count(T t);

    public T select(T t);

    public PageInfo<T> page(int pageNum, int pageSize, T t);
}
