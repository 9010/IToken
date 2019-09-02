package cn.com.itoken.common.web.service;

import cn.com.itoken.common.hystrix.Fallback;

public interface BaseClientService {
    default String page(int pageNum, int pageSize, String domainJson){
        return Fallback.badGateway();
    }
}
