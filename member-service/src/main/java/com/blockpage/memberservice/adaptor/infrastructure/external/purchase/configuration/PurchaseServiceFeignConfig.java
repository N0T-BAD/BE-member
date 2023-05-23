package com.blockpage.memberservice.adaptor.infrastructure.external.purchase.configuration;

import com.blockpage.memberservice.exception.PurchaseOpenFeignErrorDecoder;
import feign.Logger.Level;
import feign.Retryer.Default;
import feign.codec.ErrorDecoder;
import java.util.concurrent.TimeUnit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PurchaseServiceFeignConfig {

    @Bean
    public Level feignLoggerLevel() {
        return Level.FULL;
    }

    @Bean
    public ErrorDecoder errorDecoder() {
        return new PurchaseOpenFeignErrorDecoder();
    }

    @Bean
    public Default retryer() {
        //0.1초 간격으로 시작해 최대 1초 간격까지 증가 & 5번까지 시도
        return new Default(100L, TimeUnit.SECONDS.toMillis(1L), 5);
    }
}