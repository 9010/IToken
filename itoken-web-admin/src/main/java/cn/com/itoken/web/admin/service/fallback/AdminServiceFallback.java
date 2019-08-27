package cn.com.itoken.web.admin.service.fallback;

import cn.com.itoken.web.admin.service.AdminService;
import cn.com.self.itoken.common.constants.HttpStatusConstants;
import cn.com.self.itoken.common.dto.BaseResult;
import cn.com.self.itoken.common.hystrix.Fallback;
import cn.com.self.itoken.common.utils.MapperUtils;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;

@Component
public class AdminServiceFallback implements AdminService {

}
