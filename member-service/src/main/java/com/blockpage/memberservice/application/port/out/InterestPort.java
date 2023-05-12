package com.blockpage.memberservice.application.port.out;

import com.blockpage.memberservice.domain.Interest;
import java.util.List;

public interface InterestPort {

    void saveInterest(Interest interest);

    List<Interest> findInterest(Long memberId);
}
