package com.blockpage.memberservice.adaptor.web.view;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class APIResponse<T> {

    private String message;

    private final T data;

    public APIResponse(T data) {
        this.data = data;
    }

    public APIResponse(String message, T data) {
        this.message = message;
        this.data = data;
    }

    public APIResponse(String message) {
        this.data = null;
        this.message = message;
    }
}
