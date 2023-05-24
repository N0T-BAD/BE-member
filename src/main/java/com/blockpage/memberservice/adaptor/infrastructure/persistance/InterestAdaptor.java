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
    public void postInterest(Interest interest) {
        interestRepository.save(InterestEntity.fromInterest(interest));
    }

    @Override
    public List<Interest> findInterest(String memberEmail) {
        List<InterestEntity> interestEntityList = interestRepository.findAllByMemberEmail(memberEmail);
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
