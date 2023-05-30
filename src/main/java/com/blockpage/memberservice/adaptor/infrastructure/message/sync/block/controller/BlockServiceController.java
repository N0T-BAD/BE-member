package com.blockpage.memberservice.adaptor.infrastructure.message.sync.block.controller;

import com.blockpage.memberservice.adaptor.infrastructure.message.sync.block.requestbody.RequestBlock;
import com.blockpage.memberservice.application.port.out.port.BlockPort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BlockServiceController implements BlockPort {

    private final BlockServiceOpenFeign blockServiceOpenFeign;

    @Override
    public ResponseEntity postBlock(String email, RequestBlock requestBlock) {
        String type = "attendance";
        blockServiceOpenFeign.postBlock(email, type, requestBlock);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
