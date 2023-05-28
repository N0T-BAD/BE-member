package com.blockpage.memberservice.application.port.in;

import com.blockpage.memberservice.adaptor.web.requestBody.RequestMember;
import com.blockpage.memberservice.application.port.out.dto.MemberDto;
import java.io.IOException;
import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

public interface MemberUseCase {

    MemberDto signInMember(SignInQuery signInQuery);

    MemberDto findMemberInfo(FindMemberQuery findMemberQuery);

    void updateMemberInfo(UpdateQuery updateQuery) throws IOException;

    @Getter
    @Builder
    class SignInQuery {

        private String email;

        private String nickname;

        private String profileImage;

        private String gender;

        public static SignInQuery toQuery(RequestMember requestMember) {
            return SignInQuery.builder()
                .email(requestMember.getEmail())
                .nickname(requestMember.getNickname())
                .profileImage(requestMember.getProfileImage() != null ? requestMember.getProfileImage() : null)
                .gender(requestMember.getGender() != null ? requestMember.getGender() : null)
                .build();
        }
    }

    @Getter
    @Builder
    class FindMemberQuery {

        private String email;
        private String type;
        private String creatorNickname;

        public static FindMemberQuery toQuery(String email, String type, RequestMember requestMember) {
            return FindMemberQuery.builder()
                .email(email)
                .type(type)
                .creatorNickname(requestMember != null ? requestMember.getCreatorNickname() : null)
                .build();
        }
    }

    @Getter
    @Builder
    class UpdateQuery {

        private String type;

        private String email;

        private String nickname;

        private String profileSkin;

        private Boolean adult;

        private String creatorNickname;

        private MultipartFile profileImage;

        public static UpdateQuery toQuery(String email, String type, RequestMember requestMember, MultipartFile profileImage) {
            return UpdateQuery.builder()
                .type(type)
                .email(email)
                .nickname(requestMember.getNickname())
                .profileSkin(requestMember.getProfileSkin())
                .adult(requestMember.getAdult())
                .creatorNickname(requestMember.getCreatorNickname())
                .profileImage(profileImage)
                .build();
        }
    }
}
