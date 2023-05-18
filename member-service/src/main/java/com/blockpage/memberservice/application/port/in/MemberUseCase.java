package com.blockpage.memberservice.application.port.in;

import com.blockpage.memberservice.application.port.out.MemberDto;
import java.io.IOException;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

public interface MemberUseCase {

    MemberDto findMemberKakao(FindQuery findQuery);

    MemberDto findMemberinfo(FindMemberQuery findMemberQuery);

    void updateMemberInfo(UpdateQuery updateQuery) throws IOException;

    @Getter
    @NoArgsConstructor
    class FindQuery {

        private Long kakaoId;

        public FindQuery(Long kakaoId) {
            this.kakaoId = kakaoId;
        }
    }

    @Getter
    @Builder
    class FindMemberQuery {

        private Long id;
        private String type;
        private String creatorNickname;

        public static FindMemberQuery toQuery(String type, Long id, RequestMember requestMember) {
            return FindMemberQuery.builder()
                .id(id != null ? id : null)
                .type(type)
                .creatorNickname(requestMember != null ? requestMember.getCreatorNickname() : null)
                .build();
        }
    }

    @Getter
    @Builder
    class UpdateQuery {

        private String type;

        private Long id;

        private String nickname;

        private String profileSkin;

        private Boolean adult;

        private String creatorNickname;

        private MultipartFile profileImage;

        public static UpdateQuery toQuery(Long id, String type, RequestMember requestMember,MultipartFile profileImage) {
            return UpdateQuery.builder()
                .type(type)
                .id(id)
                .nickname(requestMember.getNickname())
                .profileSkin(requestMember.getProfileSkin())
                .adult(requestMember.getAdult())
                .creatorNickname(requestMember.getCreatorNickname())
                .profileImage(profileImage)
                .build();
        }
    }
}
