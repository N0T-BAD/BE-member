package com.blockpage.memberservice.application.service;

import com.blockpage.memberservice.adaptor.infrastructure.entity.MemberEntity;
import com.blockpage.memberservice.adaptor.infrastructure.repository.MemberRepository;
import com.blockpage.memberservice.adaptor.infrastructure.view.Role;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class OAuthService {

    private final MemberRepository memberRepository;

    public String kakaoLogin(String code) throws JsonProcessingException {
        //1.코드로 토큰요청
        String accessToken = getAccesssToken(code);
        //2.토큰 정보 조회(진짜 회원인가 and 이미 가입된 회원인가?)
        Long getTokenInfo = getTokenInfo(accessToken);
        Optional<MemberEntity> alreadyMember = memberRepository.findByKakaoId(getTokenInfo);
        if (alreadyMember.isPresent()) {
            String email = alreadyMember.get().getEmail();
            return email;
        } else {
            String email = getMemberInfo(accessToken);
            return email;
        }
    }

    //코드로 토큰받아오기
    private String getAccesssToken(String code) throws JsonProcessingException {
        //HTTP Header 생성
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        //Body 생성
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "authorization_code");
        body.add("client_id", "5988949f6eb75610269e9a413b832fd6");
        body.add("redirect_uri", "http://localhost:8080/v1/oauth/login");
        body.add("code", code);

        //토큰 요청
        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(body, headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(
            "https://kauth.kakao.com/oauth/token",
            HttpMethod.POST,
            kakaoTokenRequest,
            String.class
        );

        String responseBody = response.getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(responseBody);
        System.out.println("accssToken: " + jsonNode.get("access_token").asText());
        System.out.println("refreshToken: " + jsonNode.get("refresh_token").asText());
        System.out.println("idToken: " + jsonNode.get("id_token").asText());
        return jsonNode.get("access_token").asText();
    }

    //토큰 정보 조회
    private Long getTokenInfo(String accessToken) throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);

        //정보요청
        HttpEntity<MultiValueMap<String, String>> kakaoTokenInfoRequest = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(
            "https://kapi.kakao.com/v1/user/access_token_info",
            HttpMethod.GET,
            kakaoTokenInfoRequest,
            String.class
        );

        //정보 가져오기
        String responseBody = response.getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(responseBody);
        System.out.println("id: " + jsonNode.get("id").asLong());
        System.out.println("expires_in: " + jsonNode.get("expires_in: " + jsonNode.get("expires_in").asLong()));

        Long kakaoId = jsonNode.get("id").asLong();
        return kakaoId;
    }

    //토큰으로 유저 정보 가져오기 & 회원가입처리
    private String getMemberInfo(String accessToken) throws JsonProcessingException {
        //HTTP Header 생성
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        //정보 요청
        HttpEntity<MultiValueMap<String, String>> kakaoUserInfoRequest = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(
            "https://kapi.kakao.com/v2/user/me",
            HttpMethod.POST,
            kakaoUserInfoRequest,
            String.class
        );

        //정보 가져오기
        String responseBody = response.getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(responseBody);

        //필수 항목들
        Long kakaoId = jsonNode.get("id").asLong();
        String email = jsonNode.get("kakao_account").get("email").asText();
        String nickname = jsonNode.get("properties").get("nickname").asText();

        //선택항목들
        String profileImage;
        if (jsonNode.get("properties").has("profile_image_url")) {
            profileImage = jsonNode.get("properties").get("profile_image_url").asText();
        } else {
            profileImage = "프로필 이미지를 선택하지 않았습니다.";
        }
        String gender;
        if (jsonNode.get("kakao_account").has("gender")) {
            gender = jsonNode.get("kakao_account").get("gender").asText();
        } else {
            gender = "성별이 입력되지 않았습니다.";
        }

        //유저정보 저장
        memberRepository.save(MemberEntity.builder()
            .kakaoId(kakaoId)
            .email(email)
            .nickname(nickname)
            .profileImage(profileImage)
            .gender(gender)
            .role(Role.MEMBER)
            .adult(false)
            .build());

        return email;
    }

}

