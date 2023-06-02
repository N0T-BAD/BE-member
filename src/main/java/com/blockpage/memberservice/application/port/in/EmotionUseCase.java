package com.blockpage.memberservice.application.port.in;

import com.blockpage.memberservice.adaptor.web.requestBody.RequestEmotion;
import com.blockpage.memberservice.application.port.out.dto.EmotionDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public interface EmotionUseCase {

    EmotionDto postEmotionQuery(PostQuery postQuery);

    EmotionDto findAllEmotionQuery(FindQuery findQuery);

    void deleteEmotionQuery(DeleteQuery deleteQuery);

    @Getter
    @Builder
    class PostQuery {

        private String memberEmail;
        private Long episodeId;
        private Long commentId;
        private Boolean emotion;

        public static PostQuery toQuery(String memberEmail, RequestEmotion requestEmotion) {
            return PostQuery.builder()
                .memberEmail(memberEmail)
                .episodeId(requestEmotion.getEpisodeId())
                .commentId(requestEmotion.getCommentId())
                .emotion(requestEmotion.getEmotion())
                .build();
        }
    }

    @Getter
    class FindQuery {

        private String memberEmail;
        private Long commentId;

        public FindQuery(String memberEmail, Long commentId) {
            this.memberEmail = memberEmail;
            this.commentId = commentId;
        }
    }

    @Getter
    @AllArgsConstructor
    class DeleteQuery {

        private Long id;

    }
}
