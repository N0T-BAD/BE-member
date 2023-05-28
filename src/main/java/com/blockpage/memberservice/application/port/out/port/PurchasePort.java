package com.blockpage.memberservice.application.port.out.port;

import com.blockpage.memberservice.adaptor.infrastructure.external.purchase.requestbody.RequestPurchase;
import org.springframework.http.ResponseEntity;

public interface PurchasePort {

    ResponseEntity postProfileSkin(String email, RequestPurchase requestPurchase);
}
