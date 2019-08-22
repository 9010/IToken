package cn.com.itoken.service.sso.service.consumer.fallback;

import cn.com.itoken.service.sso.service.consumer.RedisService;
import cn.com.self.itoken.common.constants.HttpStatusConstants;
import cn.com.self.itoken.common.dto.BaseResult;
import cn.com.self.itoken.common.hystrix.Fallback;
import cn.com.self.itoken.common.utils.MapperUtils;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;

@Component
public class RedisServiceFallback implements RedisService {
    @Override
    public String put(String key, String value, long seconds) {
        return Fallback.badGateway();
    }

    @Override
    public String get(String key) {
        return Fallback.badGateway();
    }
}
