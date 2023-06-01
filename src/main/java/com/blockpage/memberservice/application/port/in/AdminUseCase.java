package com.blockpage.memberservice.application.port.in;

import com.blockpage.memberservice.adaptor.web.requestBody.RequestAdmin;
import com.blockpage.memberservice.application.port.out.dto.AdminDto;
import javax.servlet.http.HttpSession;
import lombok.Builder;
import lombok.Getter;

public interface AdminUseCase {

    AdminDto adminLogin(LogInQuery logInQuery);

    void sessionInfo(InfoQuery infoQuery);

    @Getter
    @Builder
    class LogInQuery {

        private String adminId;
        private String password;

        public static LogInQuery toQuery(RequestAdmin requestAdmin) {
            return LogInQuery.builder()
                .adminId(requestAdmin.getAdminId())
                .password(requestAdmin.getPassword())
                .build();
        }
    }


    @Getter
    class InfoQuery {

        private String adminId;

        public InfoQuery(HttpSession session) {
            this.adminId = session.getAttribute("id").toString();
        }
    }
}