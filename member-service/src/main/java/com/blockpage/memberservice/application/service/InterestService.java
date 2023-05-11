package com.blockpage.memberservice.application.service;

import com.blockpage.memberservice.adaptor.infrastructure.entity.MemberEntity;
import com.blockpage.memberservice.application.port.in.InterestUseCase;
import com.blockpage.memberservice.application.port.out.InterestDto;
import com.blockpage.memberservice.application.port.out.InterestPort;
import com.blockpage.memberservice.domain.Interest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InterestService implements InterestUseCase {

    private final InterestPort interestPort;

    @Override
    public InterestDto saveInterestQuery(SaveQuery saveQuery, MemberEntity memberEntity) {
        Interest interest = Interest.addInterest(saveQuery, memberEntity);
        interestPort.saveInterest(interest);
        return InterestDto.toQuery(saveQuery);
    }

}
