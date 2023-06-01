package com.blockpage.memberservice.application.port.out.port;

import com.blockpage.memberservice.domain.Admin;

public interface AdminPort {
    Admin login(Admin admin);

    void info(Admin admin);
}