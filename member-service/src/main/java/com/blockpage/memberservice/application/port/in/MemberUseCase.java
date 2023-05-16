package com.blockpage.memberservice.application.port.in;

import com.blockpage.memberservice.adaptor.infrastructure.view.Role;
import com.blockpage.memberservice.application.port.out.MemberDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public interface MemberUseCase {

    MemberDto findMemberKakao(FindQuery findQuery);

    MemberDto findMemberinfo(FindMemberQuery findMemberQuery);

    void updateMemberInfo(UpdateQuery updateQuery);

    @Getter
    @NoArgsConstructor
    class FindQuery {

        private Long kakaoId;

        public FindQuery(Long kakaoId) {
            this.kakaoId = kakaoId;
        }
    }


    @Getter
    class FindMemberQuery {

        private Long id;

        public FindMemberQuery(Long id) {
            this.id = id;
        }

    }

    @Getter
    @Builder
    class UpdateQuery {

        private Long id;

        private String email;

        private String nickname;

        private String profileImage;

        private String profileSkin;

        private String gender;

        private Role role;

        private String creatorNickname;

        public static UpdateQuery toQuery(Long id, RequestMember requestMember) {
            return UpdateQuery.builder()
                .id(id)
                .email(requestMember.getEmail())
                .nickname(requestMember.getNickname())
                .profileImage(requestMember.getProfileImage())
                .gender(requestMember.getGender())
                .role(requestMember.getRole())
                .creatorNickname(requestMember.getCreatorNickname())
                .build();
        }

    }
}
