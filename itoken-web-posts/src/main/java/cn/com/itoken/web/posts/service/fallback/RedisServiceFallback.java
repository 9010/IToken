package cn.com.itoken.web.posts.service.fallback;

import cn.com.itoken.common.hystrix.Fallback;
import cn.com.itoken.web.posts.service.RedisService;
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
