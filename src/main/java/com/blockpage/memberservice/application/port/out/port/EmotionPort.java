package com.blockpage.memberservice.application.port.out.port;

import com.blockpage.memberservice.domain.Emotion;

public interface EmotionPort {

    Emotion postEmotion(Emotion emotion); //댓글저장

    Emotion findEmotion(Emotion emotion); //에피소드 조회시 댓글 리스트

    Emotion deleteEmotion(Emotion emotion);//댓글삭제

}
