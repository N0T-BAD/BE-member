package com.blockpage.memberservice.application.service;

import com.blockpage.memberservice.adaptor.infrastructure.entity.MemberEntity;
import com.blockpage.memberservice.application.port.in.EmotionUseCase;
import com.blockpage.memberservice.application.port.out.EmotionDto;
import com.blockpage.memberservice.application.port.out.EmotionPort;
import com.blockpage.memberservice.domain.Emotion;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmotionService implements EmotionUseCase {

    private final EmotionPort emotionPort;

    @Override
    public String saveEmotionQuery(SaveQuery saveQuery, MemberEntity memberEntity) {
        //이미 반응 등록한 댓글인지 확인 후 등록 처리
        Emotion emotion = Emotion.saveEmotion(saveQuery, memberEntity);
        if (emotionPort.findEmotion(emotion) == null) {
            emotionPort.saveEmotion(emotion);
            return "댓글 반응이 등록되었습니다.";
        } else if (emotionPort.findEmotion(emotion).getEmotion() != emotion.getEmotion()) {
            return "이미 반응한 댓글입니다.";
        } else {
            //같은 반응 클릭시 삭제 처리
            emotionPort.deleteEmotion(Emotion.findEmotion(emotionPort.findEmotion(emotion)));
            return "댓글 반응이 삭제 되었습니다";
        }
    }

    @Override
    public List<EmotionDto> findAllEmotionQuery(FindQuery findQuery, MemberEntity memberEntity) {
        Emotion emotion = new Emotion(findQuery);
        List<EmotionDto> emotionDtoList = emotionPort.findAllEmotion(emotion, memberEntity.getId());
        return emotionDtoList;
    }

    @Override
    public void deleteEmotionQuery(DeleteQuery deleteQuery) {
        Emotion emotion = Emotion.deleteEmotion(deleteQuery);
        emotionPort.deleteEmotion(emotion);
    }
}
