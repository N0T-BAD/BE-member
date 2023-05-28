package com.blockpage.memberservice.adaptor.infrastructure.persistance;

import com.blockpage.memberservice.adaptor.infrastructure.entity.InterestEntity;
import com.blockpage.memberservice.adaptor.infrastructure.repository.InterestRepository;
import com.blockpage.memberservice.application.port.out.port.InterestPort;
import com.blockpage.memberservice.domain.Interest;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class InterestAdaptor implements InterestPort {

    private final InterestRepository interestRepository;

    @Override
    @Transactional
    public void postInterest(Interest interest) {
        interestRepository.save(InterestEntity.fromInterest(interest));
    }

    @Override
    @Transactional
    public List<Interest> findInterest(String memberEmail) {
        List<InterestEntity> interestEntityList = interestRepository.findAllByMemberEmail(memberEmail);
        List<Interest> interestList = interestEntityList.stream()
            .map(interestEntity -> Interest.findInterest(interestEntity))
            .collect(Collectors.toList());
        return interestList;
    }

    @Override
    public Interest findWebtoonInterest(Interest interest) {
        Optional<InterestEntity> interestEntity = interestRepository.findByMemberEmailAndWebtoonId(interest.getMemberEmail(),
            interest.getWebtoonId());
        if (interestEntity.isPresent()) {
            return new Interest(true);
        } else {
            return new Interest(false);
        }
    }

    @Override
    @Transactional
    public void deleteInterest(Long id) {
        interestRepository.deleteById(id);
    }
}
