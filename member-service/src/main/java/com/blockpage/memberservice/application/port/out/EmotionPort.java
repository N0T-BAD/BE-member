package com.blockpage.memberservice.application.port.out;

import com.blockpage.memberservice.domain.Emotion;
import java.util.List;

public interface EmotionPort {

    void saveEmotion(Emotion emotion); //댓글저장

    EmotionDto findEmotion(Emotion emotion); //댓글반응 등록상태 확인용

    List<EmotionDto> findAllEmotion(Emotion emotion, Long memberId); //에피소드 조회시 댓글 리스트

    void deleteEmotion(Emotion emotion);//댓글삭제

}
