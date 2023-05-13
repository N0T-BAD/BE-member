package com.blockpage.memberservice.adaptor.infrastructure.persistance;

import com.blockpage.memberservice.adaptor.infrastructure.entity.EmotionEntity;
import com.blockpage.memberservice.adaptor.infrastructure.repository.EmotionRepository;
import com.blockpage.memberservice.application.port.out.EmotionDto;
import com.blockpage.memberservice.application.port.out.EmotionPort;
import com.blockpage.memberservice.domain.Emotion;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmotionAdaptor implements EmotionPort {

    private final EmotionRepository emotionRepository;

    @Override
    public void saveEmotion(Emotion emotion) {
        emotionRepository.save(EmotionEntity.fromEmotion(emotion));
    }

    @Override
    public EmotionDto findEmotion(Emotion emotion) {
        Optional<EmotionEntity> emotionEntity = emotionRepository.findByMemberEntityIdAndEpisodeIdAndCommentId(
            emotion.getMemberEntity().getId(), emotion.getEpisodeId(), emotion.getCommentId());
        if (emotionEntity.isPresent()) {
            return EmotionDto.fromEmotionEntity(emotionEntity.get());
        } else {
            return null;
        }
    }

    @Override
    public List<EmotionDto> findAllEmotion(Emotion emotion, Long memberId) {
        List<EmotionEntity> emotionEntityList = emotionRepository.findAllByMemberEntityIdAndEpisodeId(memberId,
            emotion.getEpisodeId());
        List<EmotionDto> emotionDtoList = emotionEntityList.stream()
            .map(emotionEntity -> EmotionDto.fromEmotionEntity(emotionEntity))
            .collect(Collectors.toList());
        return emotionDtoList;
    }

    @Override
    public void deleteEmotion(Emotion emotion) {
        emotionRepository.deleteById(emotion.getId());
    }
}
