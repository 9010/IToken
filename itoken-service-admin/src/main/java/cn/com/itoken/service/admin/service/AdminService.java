package cn.com.itoken.service.admin.service;


import cn.com.itoken.common.domain.TbSysUser;

public interface AdminService {
    /**
     * 注册
     * @param tbSysUser
     */
    public void register(TbSysUser tbSysUser);

    /**
     * 登录
     * @param account
     * @param password
     */
    public TbSysUser login(String account, String password);
}
