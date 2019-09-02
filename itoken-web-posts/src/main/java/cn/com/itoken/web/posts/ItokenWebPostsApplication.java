package cn.com.itoken.web.posts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = "cn.com.itoken")
@EnableDiscoveryClient
@EnableFeignClients
public class ItokenWebPostsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ItokenWebPostsApplication.class, args);
    }

}
