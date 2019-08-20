package cn.com.itoken.web.admin.service.fallback;

import cn.com.itoken.web.admin.service.AdminService;
import cn.com.self.itoken.common.constants.HttpStatusConstants;
import cn.com.self.itoken.common.dto.BaseResult;
import cn.com.self.itoken.common.utils.MapperUtils;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;

@Component
public class AdminServiceFallback implements AdminService {

    @Override
    public String login(String loginCode, String password) {
        BaseResult baseResult = BaseResult.notOk(Lists.newArrayList(
                new BaseResult.Error
                    (String.valueOf(HttpStatusConstants.BAD_GATWAY.getStatus()),
                    HttpStatusConstants.BAD_GATWAY.getContent())));
        try {
            return MapperUtils.obj2json(baseResult);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
