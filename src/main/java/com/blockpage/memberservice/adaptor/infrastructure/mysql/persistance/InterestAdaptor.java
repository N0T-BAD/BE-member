package com.blockpage.memberservice.adaptor.infrastructure.mysql.persistance;

import static com.blockpage.memberservice.exception.ErrorCode.*;

import com.blockpage.memberservice.adaptor.infrastructure.mysql.entity.InterestEntity;
import com.blockpage.memberservice.adaptor.infrastructure.mysql.repository.InterestRepository;
import com.blockpage.memberservice.application.port.out.port.InterestPort;
import com.blockpage.memberservice.domain.Interest;
import com.blockpage.memberservice.exception.CustomException;
import java.time.LocalDateTime;
import java.util.Comparator;
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
        Optional<InterestEntity> interestEntity = interestRepository.findByMemberEmailAndWebtoonId(interest.getMemberEmail(),
            interest.getWebtoonId());
        if (interestEntity.isEmpty()) {
            interestRepository.save(InterestEntity.fromInterest(interest));
        } else {
            interestEntity.get().setErase(Boolean.FALSE);
            interestRepository.save(interestEntity.get());
        }
    }

    @Override
    @Transactional
    public List<Interest> findInterest(String memberEmail) {
        List<InterestEntity> interestEntityList = interestRepository.findAllByMemberEmail(memberEmail);
        List<Interest> interestList = interestEntityList.stream()
            .filter(interestEntity -> interestEntity.getErase() == Boolean.FALSE)
            .sorted(Comparator.comparing(InterestEntity::getUpdateTime).reversed())
            .map(interestEntity -> Interest.findInterest(interestEntity))
            .collect(Collectors.toList());
        return interestList;
    }

    @Override
    public Interest findWebtoonInterest(Interest interest) {
        Optional<InterestEntity> interestEntity = interestRepository.findByMemberEmailAndWebtoonIdAndEraseFalse(interest.getMemberEmail(),
            interest.getWebtoonId());
        if (interestEntity.isPresent()) {
            return new Interest(true, interestEntity.get().getId());
        } else {
            return new Interest(false, 0L);
        }
    }

    @Override
    @Transactional
    public Interest deleteInterest(Long id) {
        InterestEntity interestEntity = interestRepository.findById(id)
            .orElseThrow(() -> new CustomException(INTEREST_NOT_EXIST.getMessage(), INTEREST_NOT_EXIST.getHttpStatus()));
        Interest interest = Interest.messageInterest(interestEntity);
        interestEntity.setErase(Boolean.TRUE);
        interestRepository.save(interestEntity);
        return interest;
    }

    @Override
    public List<Interest> findInterestByUpdateDate(LocalDateTime start, LocalDateTime end) {
        List<Interest> interestList = interestRepository.findAllByUpdateTimeBetween(start, end).stream()
            .map(Interest::findInterest)
            .collect(Collectors.toList());
        return interestList;
    }
}
