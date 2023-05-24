package com.blockpage.memberservice.application.service;

import com.blockpage.memberservice.application.port.in.InterestUseCase;
import com.blockpage.memberservice.application.port.out.InterestDto;
import com.blockpage.memberservice.application.port.out.InterestPort;
import com.blockpage.memberservice.domain.Interest;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InterestService implements InterestUseCase {

    private final InterestPort interestPort;

    @Override
    public InterestDto postInterestQuery(PostQuery postQuery) {
        interestPort.postInterest(Interest.postInterest(postQuery));
        return InterestDto.toQuery(postQuery);
    }

    @Override
    public List<InterestDto> findInterestQuery(FindQuery findQuery) {
        List<InterestDto> interestDtoList = interestPort.findInterest(findQuery.getMemberEmail()).stream()
            .map(interest -> InterestDto.fromInterest(interest))
            .collect(Collectors.toList());

        return interestDtoList;
    }

    @Override
    public void deleteInterestQuery(DeleteQuery query) {
        interestPort.deleteInterest(query.getId());

    }

}
