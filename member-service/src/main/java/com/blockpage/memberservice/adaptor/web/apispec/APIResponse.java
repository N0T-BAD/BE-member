package com.blockpage.memberservice.adaptor.web.apispec;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class APIResponse<T> {

    private final T data;

    public APIResponse(T data) {
        this.data = data;
    }
}
