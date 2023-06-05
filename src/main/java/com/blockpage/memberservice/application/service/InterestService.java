package com.blockpage.memberservice.application.service;

import com.blockpage.memberservice.application.port.in.InterestUseCase;
import com.blockpage.memberservice.application.port.out.dto.InterestDto;
import com.blockpage.memberservice.application.port.out.port.InterestPort;
import com.blockpage.memberservice.domain.Interest;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
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
    public InterestDto findInterestWebtoonQuery(FindWebtoonQuery query) {
        Interest interest = interestPort.findWebtoonInterest(Interest.findEpisodeInterest(query));
        return new InterestDto(interest.getChoice(), interest.getId());
    }

    @Override
    public void deleteInterestQuery(DeleteQuery query) {
        Interest interest = interestPort.deleteInterest(query.getId());
    }
}
