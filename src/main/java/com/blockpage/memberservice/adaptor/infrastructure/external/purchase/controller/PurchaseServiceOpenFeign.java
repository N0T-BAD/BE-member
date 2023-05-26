package com.blockpage.memberservice.adaptor.infrastructure.external.purchase.controller;

import com.blockpage.memberservice.adaptor.infrastructure.external.purchase.configuration.PurchaseServiceFeignConfig;
import com.blockpage.memberservice.adaptor.infrastructure.external.purchase.requestbody.RequestPurchase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "purchase-service",
    configuration = PurchaseServiceFeignConfig.class,
    fallback = PurchaseServiceOpenFeign.Fallback.class)
public interface PurchaseServiceOpenFeign {

    @PostMapping(value = "/purchase-service/v1/purchases")
    ResponseEntity postProfileSkin(@RequestHeader String email,
        @RequestParam("type") String type,
        @RequestBody RequestPurchase requestPurchase);

    @Component
    @Slf4j
    class Fallback implements PurchaseServiceOpenFeign {

        @Override
        public ResponseEntity postProfileSkin(String email,
            String type,
            RequestPurchase requestPurchase) {
            log.info("재시도");
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
    }
}
