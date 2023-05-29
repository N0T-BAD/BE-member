package com.blockpage.memberservice.adaptor.infrastructure.message.sync.block.controller;

import com.blockpage.memberservice.adaptor.infrastructure.message.sync.block.configuration.BlockServiceFeignConfig;
import com.blockpage.memberservice.adaptor.infrastructure.message.sync.block.requestbody.RequestBlock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "block-service",
    configuration = BlockServiceFeignConfig.class,
    fallback = BlockServiceOpenFeign.Fallback.class)
public interface BlockServiceOpenFeign {

    @PostMapping(value = "/block-service/v1/blocks")
    ResponseEntity postBlock(@RequestHeader String email,
        @RequestParam("type") String type,
        @RequestBody RequestBlock requestBlock);

    @Component
    @Slf4j
    class Fallback implements BlockServiceOpenFeign {

        @Override
        public ResponseEntity postBlock(String email,String type, RequestBlock requestBlock) {
            log.info("재시도");
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
    }
}
