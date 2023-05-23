package com.blockpage.memberservice.adaptor.infrastructure.external.block.requestbody;

import lombok.Getter;

@Getter
public class RequestBlock {

    private Integer blockQuantity;

    public RequestBlock(String email) {
        this.blockQuantity = 4;
    }
}