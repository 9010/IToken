package cn.com.itoken.service.posts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication(scanBasePackages = "cn.com.itoken")
@EnableEurekaClient
@MapperScan(basePackages = {"cn.com.itoken.common.service.mapper", "cn.com.itoken.service.posts.mapper"})
public class ItokenServicePostsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ItokenServicePostsApplication.class, args);
    }

}
