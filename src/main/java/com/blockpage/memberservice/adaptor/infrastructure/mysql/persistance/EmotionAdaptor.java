package com.blockpage.memberservice.adaptor.infrastructure.mysql.persistance;

import static com.blockpage.memberservice.exception.ErrorCode.*;

import com.blockpage.memberservice.adaptor.infrastructure.mysql.entity.EmotionEntity;
import com.blockpage.memberservice.adaptor.infrastructure.mysql.repository.EmotionRepository;
import com.blockpage.memberservice.application.port.out.port.EmotionPort;
import com.blockpage.memberservice.domain.Emotion;
import com.blockpage.memberservice.exception.CustomException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class EmotionAdaptor implements EmotionPort {

    private final EmotionRepository emotionRepository;

    @Override
    @Transactional
    public Emotion postEmotion(Emotion emotion) {
        Optional<EmotionEntity> emotionEntity = emotionRepository.findByMemberEmailAndEpisodeIdAndCommentId(
            emotion.getMemberEmail(), emotion.getEpisodeId(), emotion.getCommentId());
        if (emotionEntity.isEmpty()) {
            emotionRepository.save(EmotionEntity.fromEmotion(emotion));
            return new Emotion(emotion.getCommentId(), emotion.getEmotion());
        } else if (emotionEntity.get().getEmotion() == emotion.getEmotion()) {
            emotionRepository.deleteById(emotionEntity.get().getId());
            return new Emotion(emotion.getCommentId(), null);
        } else {
            throw new CustomException(EMOTION_ALREADY_POST.getMessage(), EMOTION_ALREADY_POST.getHttpStatus());
        }
    }

    @Override
    @Transactional
    public List<Emotion> findAllEmotion(Emotion emotion) {
        List<EmotionEntity> emotionEntityList = emotionRepository.findAllByMemberEmailAndEpisodeId(emotion.getMemberEmail(),
            emotion.getEpisodeId());
        List<Emotion> emotionList = emotionEntityList.stream()
            .map(emotionEntity -> Emotion.fromEntity(emotionEntity))
            .collect(Collectors.toList());
        return emotionList;
    }

    @Override
    @Transactional
    public Emotion deleteEmotion(Emotion emotion) {
        EmotionEntity emotionEntity = emotionRepository.findById(emotion.getId()).get();
        emotionRepository.deleteById(emotion.getId());
        return new Emotion(emotionEntity.getCommentId(), emotionEntity.getEmotion());
    }
}
