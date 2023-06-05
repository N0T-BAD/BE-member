package com.blockpage.memberservice.domain;

import com.blockpage.memberservice.adaptor.infrastructure.mysql.entity.MemberEntity;
import com.blockpage.memberservice.adaptor.infrastructure.mysql.value.Role;
import com.blockpage.memberservice.application.port.in.MemberUseCase.FindMemberQuery;
import com.blockpage.memberservice.application.port.in.MemberUseCase.SignInQuery;
import com.blockpage.memberservice.application.port.in.MemberUseCase.UpdateQuery;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Builder
@AllArgsConstructor
public class Member {

    private Long id;

    private String email;

    private String nickname;

    private String profileImage;

    private MultipartFile newProfileImage;

    private String profileSkin;

    private String gender;

    private Role role;

    private String creatorNickname;

    private Boolean adult;

    private Boolean signUp;

    private String type;

    public static Member findMemberInfo(FindMemberQuery findMemberQuery) {
        return Member.builder()
            .email(findMemberQuery.getEmail())
            .build();
    }

    public static Member findNickname(FindMemberQuery findMemberQuery) {
        return Member.builder()
            .creatorNickname(findMemberQuery.getCreatorNickname())
            .build();
    }

    public static Member signInMember(SignInQuery signInQuery) {
        return Member.builder()
            .email(signInQuery.getEmail())
            .nickname(signInQuery.getNickname())
            .profileImage(signInQuery.getProfileImage())
            .gender(signInQuery.getGender())
            .role(Role.MEMBER)
            .build();
    }

    public static Member fromMemberEntity(MemberEntity memberEntity) {
        return Member.builder()
            .id(memberEntity.getId())
            .email(memberEntity.getEmail())
            .nickname(memberEntity.getNickname())
            .profileImage(memberEntity.getProfileImage())
            .profileSkin(memberEntity.getProfileSkin())
            .gender(memberEntity.getGender())
            .role(memberEntity.getRole())
            .creatorNickname(memberEntity.getCreatorNickname())
            .adult(memberEntity.getAdult())
            .build();
    }

    public static Member fromUpdateQuery(UpdateQuery updateQuery) {
        return Member.builder()
            .type(updateQuery.getType())
            .email(updateQuery.getEmail())
            .nickname(updateQuery.getNickname())
            .profileImage(updateQuery.getProfileImage())
            .newProfileImage(updateQuery.getNewProfileImage())
            .profileSkin(updateQuery.getProfileSkin())
            .creatorNickname(updateQuery.getCreatorNickname())
            .adult(updateQuery.getAdult())
            .build();
    }

    public static Member afterSignIn(Boolean signUp, Role role) {
        return Member.builder()
            .role(role)
            .signUp(signUp)
            .build();
    }
}
