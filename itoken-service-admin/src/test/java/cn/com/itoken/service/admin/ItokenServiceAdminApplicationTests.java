package cn.com.itoken.service.admin;

import cn.com.itoken.service.admin.domain.TbSysUser;
import cn.com.itoken.service.admin.service.AdminService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ItokenServiceAdminApplicationTests {

    @Autowired
    private AdminService adminService;

    @Test
    public void register() {
        TbSysUser tbSysUser = new TbSysUser();
        tbSysUser.setLoginCode("test");
        tbSysUser.setPassword("123456");
        adminService.register(tbSysUser);
    }

    @Test
    public void login() {
        TbSysUser tbSysUser =  adminService.login("test", "123456");
        Assert.assertNotNull(tbSysUser);
    }


}
