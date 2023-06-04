package com.blockpage.memberservice.adaptor.web.view;

import com.blockpage.memberservice.adaptor.infrastructure.mysql.value.Role;
import com.blockpage.memberservice.application.port.out.dto.AdminDto;
import com.blockpage.memberservice.application.port.out.dto.InterestDto;
import com.blockpage.memberservice.application.port.out.dto.MemberDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MemberView {

    private Long id;

    private String message;

    //MemberView

    private String nickname;

    private String profileImage;

    private String profileSkin;

    private Role role;

    private String creatorNickname;

    //InterestView
    private Long webtoonId;

    private String webtoonTitle;

    private String webtoonThumbnail;

    private String creator;

    private String illustrator;

    private String genre;

    //EmotionView
    private Long commentId;

    private Boolean choice;

    private Boolean emotion;

    //RatingView
    private Integer ratings;

    //AdminView
    private String adminId;

    private String name;

    //찜 개별조회
    public MemberView(Long id, Boolean choice) {
        this.id = id;
        this.choice = choice;
    }

    //평점조회
    public MemberView(Integer ratings) {
        this.ratings = ratings;
    }

    public MemberView(InterestDto interestDto) {
        this.id = interestDto.getId();
        this.webtoonId = interestDto.getWebtoonId();
        this.webtoonTitle = interestDto.getWebtoonTitle();
        this.webtoonThumbnail = interestDto.getWebtoonThumbnail();
        this.creator = interestDto.getCreator();
        this.illustrator = interestDto.getIllustrator();
        this.genre = interestDto.getGenre();
    }

    public MemberView(String message) {
        this.message = message;
    }

    public MemberView(MemberDto memberDto) {
        this.nickname = memberDto.getNickname();
        this.profileImage = memberDto.getProfileImage();
        this.profileSkin = memberDto.getProfileSkin();
        this.role = memberDto.getRole();
        this.creatorNickname = memberDto.getCreatorNickname();

    }

    public MemberView(String message, Boolean emotion) {
        this.message = message;
        this.emotion = emotion;
    }

    public MemberView(Boolean choice, Boolean emotion) {
        this.choice = choice;
        this.emotion = emotion;
    }

    public MemberView(AdminDto adminDto) {
        this.name = adminDto.getName();
    }
}
