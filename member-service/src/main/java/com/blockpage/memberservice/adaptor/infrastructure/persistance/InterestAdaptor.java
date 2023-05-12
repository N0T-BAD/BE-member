package com.blockpage.memberservice.adaptor.infrastructure.persistance;

import com.blockpage.memberservice.adaptor.infrastructure.entity.InterestEntity;
import com.blockpage.memberservice.adaptor.infrastructure.repository.InterestRepository;
import com.blockpage.memberservice.application.port.out.InterestPort;
import com.blockpage.memberservice.domain.Interest;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InterestAdaptor implements InterestPort {

    private final InterestRepository interestRepository;

    @Override
    public void saveInterest(Interest interest) {
        interestRepository.save(InterestEntity.builder()
            .memberEntity(interest.getMemberEntity())
            .webtoonId(interest.getWebtoonId())
            .webtoonTitle(interest.getWebtoonTitle())
            .creator(interest.getCreator())
            .illustrator(interest.getIllustrator())
            .webtoonThumbnail(interest.getWebtoonThumbnail())
            .genre(interest.getGenre())
            .build());
    }

    @Override
    public List<Interest> findInterest(Long memberId) {
        List<InterestEntity> interestEntityList = interestRepository.findAllByMemberEntityId(memberId);
        System.out.println(interestEntityList.toString());
        List<Interest> interestList = interestEntityList.stream()
            .map(interestEntity -> Interest.findInterest(interestEntity))
            .collect(Collectors.toList());
        return interestList;
    }

    @Override
    public void deleteInterest(Long id) {
        interestRepository.deleteById(id);
    }
}
