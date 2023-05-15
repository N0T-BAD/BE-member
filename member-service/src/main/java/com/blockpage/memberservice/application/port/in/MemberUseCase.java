package com.blockpage.memberservice.application.port.in;

import com.blockpage.memberservice.application.port.out.MemberDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

public interface MemberUseCase {

    MemberDto findMemberKakao(FindQuery findQuery);

    MemberDto findMemberinfo(FindMemberQuery findMemberQuery);

    @Getter
    @NoArgsConstructor
    class FindQuery {

        private Long kakaoId;

        public FindQuery(Long kakaoId) {
            this.kakaoId = kakaoId;
        }
    }


    @Getter
    @NoArgsConstructor
    class FindMemberQuery {

        private Long id;

        public FindMemberQuery(Long id) {
            this.id = id;
        }

    }
}
