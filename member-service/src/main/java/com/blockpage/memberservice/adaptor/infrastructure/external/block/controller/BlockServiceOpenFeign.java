package com.blockpage.memberservice.adaptor.infrastructure.external.block.controller;

import com.blockpage.memberservice.adaptor.infrastructure.external.block.requestbody.RequestBlock;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "block-service", url = "${block.root}")
public interface BlockServiceOpenFeign {

    @PostMapping
    ResponseEntity postBlock(@RequestParam("type") String type, @RequestBody RequestBlock requestBlock);
}
