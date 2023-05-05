package com.blockpage.memberservice.adaptor.web.view;


import com.blockpage.memberservice.adaptor.infrastructure.view.Role;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MemberView {

    private Long id;

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

    private Boolean emotion;

    //RatingView
    private Integer ratings;

    //회원조회
    public MemberView(String nickname, String profileImage, String profileSkin, Role role, String creatorNickname) {
        this.nickname = nickname;
        this.profileImage = profileImage;
        this.profileSkin = profileSkin;
        this.role = role;
        this.creatorNickname = creatorNickname;
    }

    //찜목록 조회
    public MemberView(Long id, Long webtoonId, String webtoonTitle, String webtoonThumbnail, String creator, String illustrator,
        String genre) {
        this.id = id;
        this.webtoonId = webtoonId;
        this.webtoonTitle = webtoonTitle;
        this.webtoonThumbnail = webtoonThumbnail;
        this.creator = creator;
        this.illustrator = illustrator;
        this.genre = genre;
    }

    //댓글반응 조회
    public MemberView(Long id, Long commentId, Boolean emotion) {
        this.id = id;
        this.commentId = commentId;
        this.emotion = emotion;
    }

    //평점조회
    public MemberView(Integer ratings) {
        this.ratings = ratings;
    }
}
