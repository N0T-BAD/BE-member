package com.blockpage.memberservice.domain;

import com.blockpage.memberservice.adaptor.infrastructure.entity.MemberEntity;
import com.blockpage.memberservice.adaptor.infrastructure.value.Role;
import com.blockpage.memberservice.application.port.in.MemberUseCase.FindMemberQuery;
import com.blockpage.memberservice.application.port.in.MemberUseCase.FindQuery;
import com.blockpage.memberservice.application.port.in.MemberUseCase.UpdateQuery;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Builder
@AllArgsConstructor
public class Member {

    private Long id;

    private String uuid;

    private String email;

    private String nickname;

    private String profileImage;

    private MultipartFile newProfileImage;

    private String profileSkin;

    private String gender;

    private Role role;

    private String creatorNickname;

    private Boolean adult;

    public static Member signInMember(FindQuery findQuery) {
        return Member.builder()
            .email(findQuery.getEmail())
            .build();
    }

    public static Member findNickname(FindMemberQuery findMemberQuery) {
        return Member.builder()
            .email(findMemberQuery.getEmail())
            .creatorNickname(findMemberQuery.getCreatorNickname())
            .build();
    }

    public static Member saveMember(FindQuery findQuery) {
        return Member.builder()
            .email(findQuery.getEmail())
            .nickname(findQuery.getNickname())
            .profileImage(findQuery.getProfileImage())
            .gender(findQuery.getGender())
            .role(Role.MEMBER)
            .build();
    }

    public static Member fromMemberEntity(MemberEntity memberEntity) {
        return Member.builder()
            .uuid(memberEntity.getUuid())
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

    public static Member fromUpdateQuery(@RequestBody UpdateQuery updateQuery) {
        return Member.builder()
            .email(updateQuery.getEmail())
            .nickname(updateQuery.getNickname())
            .newProfileImage(updateQuery.getProfileImage())
            .adult(updateQuery.getAdult())
            .build();
    }

    public static Member updateCreator(UpdateQuery updateQuery) {
        return Member.builder()
            .email(updateQuery.getEmail())
            .role(Role.AUTHOR)
            .creatorNickname(updateQuery.getCreatorNickname())
            .build();
    }
}
