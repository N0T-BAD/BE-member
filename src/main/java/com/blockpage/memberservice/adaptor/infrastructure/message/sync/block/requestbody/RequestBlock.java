package com.blockpage.memberservice.adaptor.infrastructure.message.sync.block.requestbody;

import lombok.Getter;

@Getter
public class RequestBlock {

    private Integer blockQuantity;

    public RequestBlock() {
        this.blockQuantity = 4;
    }
}