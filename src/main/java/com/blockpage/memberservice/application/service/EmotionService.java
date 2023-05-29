package com.blockpage.memberservice.application.service;

import com.blockpage.memberservice.adaptor.infrastructure.message.async.comment.message.CommentCountMessage;
import com.blockpage.memberservice.application.port.in.EmotionUseCase;
import com.blockpage.memberservice.application.port.out.dto.EmotionDto;
import com.blockpage.memberservice.application.port.out.port.CommentMessagePort;
import com.blockpage.memberservice.application.port.out.port.EmotionPort;
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
    private final CommentMessagePort commentMessagePort;
    private final Integer COUNT_UP = 1;
    private final Integer COUNT_DOWN = -1;
    private final Integer ZERO = 0;

    @Override
    public EmotionDto postEmotionQuery(PostQuery postQuery) {
        Emotion emotion = emotionPort.postEmotion(Emotion.postEmotion(postQuery));
        if (emotion.getEmotion() == (Boolean) true) {
            commentMessagePort.sendCommentCount(CommentCountMessage.sendMessage(emotion.getCommentId(), COUNT_UP, ZERO));
            return new EmotionDto(emotion.getEmotion());
        } else if (emotion.getEmotion() == (Boolean) false) {
            commentMessagePort.sendCommentCount(CommentCountMessage.sendMessage(emotion.getCommentId(), ZERO, COUNT_UP));
            return new EmotionDto(emotion.getEmotion());
        } else if (emotion.getEmotion() == null && postQuery.getEmotion() == (Boolean) true) {
            commentMessagePort.sendCommentCount(CommentCountMessage.sendMessage(emotion.getCommentId(), COUNT_DOWN, ZERO));
            return new EmotionDto(null);
        } else {
            commentMessagePort.sendCommentCount(CommentCountMessage.sendMessage(emotion.getCommentId(), ZERO, COUNT_DOWN));
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
        Emotion emotion = emotionPort.deleteEmotion(Emotion.deleteEmotion(deleteQuery));
        if (emotion.getEmotion() == (Boolean) true) {
            commentMessagePort.sendCommentCount(CommentCountMessage.sendMessage(emotion.getCommentId(), COUNT_DOWN, ZERO));
        } else {
            commentMessagePort.sendCommentCount(CommentCountMessage.sendMessage(emotion.getCommentId(), ZERO, COUNT_DOWN));
        }
    }
}
