package com.blockpage.memberservice.application.port.out.port;

import com.blockpage.memberservice.domain.Interest;
import java.util.List;

public interface InterestPort {

    void postInterest(Interest interest);

    List<Interest> findInterest(String memberEmail);

    Interest findWebtoonInterest(Interest interest);

    Interest deleteInterest(Long id);
}
