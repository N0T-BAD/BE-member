package com.blockpage.memberservice.application.port.out;

import com.blockpage.memberservice.adaptor.infrastructure.external.block.requestbody.RequestBlock;
import org.springframework.http.ResponseEntity;

public interface BlockPort {

    ResponseEntity postBlock(String email,RequestBlock requestBlock);
}
