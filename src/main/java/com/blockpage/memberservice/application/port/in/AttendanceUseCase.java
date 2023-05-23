package com.blockpage.memberservice.application.port.in;

import lombok.Getter;

public interface AttendanceUseCase {

    void postAttendance(PostQuery postQuery);

    @Getter
    class PostQuery {

        String memberEmail;


        public PostQuery(String email) {
            this.memberEmail = email;
        }
    }
}
