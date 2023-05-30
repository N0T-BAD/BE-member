package com.blockpage.memberservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableJpaAuditing
@EnableScheduling
@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication
public class MemberServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MemberServiceApplication.class, args);
    }

}
