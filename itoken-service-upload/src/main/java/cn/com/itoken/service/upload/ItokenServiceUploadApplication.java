package cn.com.itoken.service.upload;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class ItokenServiceUploadApplication {

    public static void main(String[] args) {
        SpringApplication.run(ItokenServiceUploadApplication.class, args);
    }

}
