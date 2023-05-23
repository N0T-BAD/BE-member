package com.blockpage.memberservice.application.port.in;

import javax.servlet.http.HttpSession;
import lombok.Getter;

public interface AttendanceUseCase {

    void postAttendance(PostQuery postQuery);

    @Getter
    class PostQuery {

        String memberEmail;


        public PostQuery(HttpSession session) {
//            String email = (String) session.getAttribute("id");
            String email = "test@naver.com";
            this.memberEmail = email;
        }
    }
}
