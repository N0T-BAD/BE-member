package com.blockpage.memberservice.adaptor.web.view;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {


    private final T data;

    public ApiResponse(T data) {
        this.data = data;
    }


}
