package com.blockpage.memberservice.adaptor.infrastructure.external.purchase.requestbody;

import lombok.Getter;

@Getter
public class RequestPurchase {

    private Long profileSkinId;
    private String persistType;
    private Integer blockQuantity;

    public RequestPurchase(String email) {
        this.profileSkinId = 1L;
        this.persistType = "permanent";
        this.blockQuantity = 0;
    }
}