package com.blockpage.memberservice.application.port.out;

import com.blockpage.memberservice.adaptor.infrastructure.external.purchase.requestbody.RequestPurchase;
import org.springframework.http.ResponseEntity;

public interface PurchasePort {

    ResponseEntity postProfileSkin(String type, RequestPurchase requestPurchase);
}
