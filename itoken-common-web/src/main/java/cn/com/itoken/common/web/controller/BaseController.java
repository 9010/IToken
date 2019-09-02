package cn.com.itoken.common.web.controller;

import cn.com.itoken.common.service.domain.BaseDomain;
import cn.com.itoken.common.web.service.BaseClientService;
import org.springframework.beans.factory.annotation.Autowired;
public abstract class BaseController<T extends BaseDomain, S extends BaseClientService> {

    /**
     * 注入业务逻辑层
     */
    @Autowired
    protected S service;

}
