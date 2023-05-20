package com.blockpage.memberservice.adaptor.infrastructure.external.purchase.controller;

import com.blockpage.memberservice.adaptor.infrastructure.external.purchase.requestbody.RequestPurchase;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "purchase-service", url = "${purchase.root}")
public interface PurchaseServiceOpenFeign {

    @PostMapping(value = "${purchase.post}")
    ResponseEntity postProfileSkin(@RequestHeader("type") String type, @RequestBody RequestPurchase requestPurchase);

}
