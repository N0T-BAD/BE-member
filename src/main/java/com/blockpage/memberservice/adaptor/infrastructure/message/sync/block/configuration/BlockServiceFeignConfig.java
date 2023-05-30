package com.blockpage.memberservice.adaptor.infrastructure.message.sync.block.configuration;

import com.blockpage.memberservice.exception.BlockOpenFeignErrorDecoder;
import feign.Logger.Level;
import feign.Retryer;
import feign.Retryer.Default;
import feign.codec.ErrorDecoder;
import java.util.concurrent.TimeUnit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BlockServiceFeignConfig {

    @Bean
    public feign.Logger.Level feignLoggerLevel() {
        return Level.FULL;
    }

    @Bean
    public ErrorDecoder errorDecoder() {
        return new BlockOpenFeignErrorDecoder();
    }

    @Bean
    public Retryer.Default retryer() {
        //0.1초 간격으로 시작해 최대 1초 간격까지 증가 & 5번까지 시도
        return new Default(100L, TimeUnit.SECONDS.toMillis(1L), 5);
    }
}