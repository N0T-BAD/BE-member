package com.blockpage.memberservice.adaptor.web.view;

import com.blockpage.memberservice.adaptor.infrastructure.value.Role;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

}
