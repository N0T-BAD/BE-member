package com.blockpage.memberservice.application.service;

import com.blockpage.memberservice.application.port.in.EmotionUseCase;
import com.blockpage.memberservice.application.port.out.EmotionDto;
import com.blockpage.memberservice.application.port.out.EmotionPort;
import com.blockpage.memberservice.domain.Emotion;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmotionService implements EmotionUseCase {

    private final EmotionPort emotionPort;

    @Override
    public EmotionDto postEmotionQuery(PostQuery postQuery) {
        Emotion emotion = emotionPort.postEmotion(Emotion.postEmotion(postQuery));
        if(emotion.getEmotion() != null){
            return new EmotionDto(emotion.getEmotion());
        }else{
            return new EmotionDto(null);
        }
    }

    @Override
    public List<EmotionDto> findAllEmotionQuery(FindQuery findQuery) {
        List<EmotionDto> emotionDtoList = emotionPort.findAllEmotion(Emotion.findEmotion(findQuery)).stream()
            .map(emotion -> EmotionDto.fromEmotion(emotion))
            .collect(Collectors.toList());
        return emotionDtoList;
    }

    @Override
    public void deleteEmotionQuery(DeleteQuery deleteQuery) {
        emotionPort.deleteEmotion(Emotion.deleteEmotion(deleteQuery));
    }
}
