package com.blockpage.memberservice.application.port.out.port;

import com.blockpage.memberservice.domain.Emotion;
import java.util.List;

public interface EmotionPort {

    Emotion postEmotion(Emotion emotion); //댓글저장

    List<Emotion> findAllEmotion(Emotion emotion); //에피소드 조회시 댓글 리스트

    Emotion deleteEmotion(Emotion emotion);//댓글삭제

}
