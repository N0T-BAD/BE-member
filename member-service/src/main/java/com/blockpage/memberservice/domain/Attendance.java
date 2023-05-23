package com.blockpage.memberservice.domain;

import lombok.Getter;

@Getter
public class Attendance {

    private String memberEmail;

    public Attendance(String memberEmail) {
        this.memberEmail = memberEmail;
    }
}
