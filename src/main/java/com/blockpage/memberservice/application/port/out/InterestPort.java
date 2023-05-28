package com.blockpage.memberservice.application.port.out;

import com.blockpage.memberservice.domain.Interest;
import java.util.List;

public interface InterestPort {

    void postInterest(Interest interest);

    List<Interest> findInterest(String memberEmail);

    Interest findWebtoonInterest(Interest interest);

    void deleteInterest(Long id);
}
