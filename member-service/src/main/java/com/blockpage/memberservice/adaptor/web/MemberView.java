package com.blockpage.memberservice.adaptor.web;

import com.blockpage.memberservice.adaptor.infrastructure.MemberEntity;
import com.blockpage.memberservice.adaptor.infrastructure.Role;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberView {

    private String nickname;

    private String profileImage;

    private String profileSkin;

    private Role role;

}
