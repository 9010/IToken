package cn.com.itoken.web.admin.service.fallback;

import cn.com.itoken.web.admin.service.AdminService;
import org.springframework.stereotype.Component;

@Component
public class AdminServiceFallback implements AdminService {

    @Override
    public String login(String loginCode, String password) {
        return null;
    }
}
