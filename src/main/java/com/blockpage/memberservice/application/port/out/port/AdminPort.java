package com.blockpage.memberservice.application.port.out.port;

import com.blockpage.memberservice.domain.Admin;

public interface AdminPort {
    Admin findId(Admin admin);

    Admin findPassword(Admin admin);
}