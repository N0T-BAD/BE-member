package com.blockpage.memberservice.application.port.in;

import com.blockpage.memberservice.adaptor.infrastructure.entity.MemberEntity;
import com.blockpage.memberservice.application.port.out.EmotionDto;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public interface EmotionUseCase {

    String saveEmotionQuery(SaveQuery saveQuery, MemberEntity memberEntity);

    List<EmotionDto> findAllEmotionQuery(FindQuery findQuery, MemberEntity memberEntity);

    void deleteEmotionQuery(DeleteQuery deleteQuery);

    @Getter
    @Builder
    class SaveQuery {

        private Long episodeId;
        private Long commentId;
        private Boolean emotion;

        public static SaveQuery toQuery(RequestEmotion requestEmotion) {
            return SaveQuery.builder()
                .episodeId(requestEmotion.getEpisodeId())
                .commentId(requestEmotion.getCommentId())
                .emotion(requestEmotion.getEmotion())
                .build();
        }
    }

    @Getter
    @AllArgsConstructor
    class FindQuery {

        private Long episodeId;

    }

    @Getter
    @AllArgsConstructor
    class DeleteQuery {

        private Long id;

    }
}
