package com.blockpage.memberservice.adaptor.infrastructure.external.purchase.controller;

import com.blockpage.memberservice.adaptor.infrastructure.external.purchase.requestbody.RequestPurchase;
import com.blockpage.memberservice.application.port.out.PurchasePort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PurchaseServiceController implements PurchasePort {

    private final PurchaseServiceOpenFeign purchaseServiceOpenFeign;

    @Override
    public ResponseEntity postProfileSkin(RequestPurchase requestPurchase) {
        String type = "profileSkin";
        purchaseServiceOpenFeign.postProfileSkin(type, requestPurchase);
        return ResponseEntity.ok().build();
    }
}