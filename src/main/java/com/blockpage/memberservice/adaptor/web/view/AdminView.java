package com.blockpage.memberservice.adaptor.web.view;

import com.fasterxml.jackson.annotation.JsonInclude;
import javax.servlet.http.HttpSession;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AdminView {

    private String message;

    private String memberId;

    private String role;

    private String sessionId;

    public AdminView(String message){
        this.message = message;
    }

    public AdminView(HttpSession session){
        this.memberId = session.getAttribute("id").toString();
        this.role = session.getAttribute("role").toString();
    }
}