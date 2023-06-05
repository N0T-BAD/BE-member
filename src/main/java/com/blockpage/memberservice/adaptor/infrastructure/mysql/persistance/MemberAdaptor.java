package com.blockpage.memberservice.adaptor.infrastructure.mysql.persistance;

import static com.blockpage.memberservice.exception.ErrorCode.NICKNAME_ALREADY_EXIST;
import static com.blockpage.memberservice.exception.ErrorCode.UNKNOWN_ERROR;

import com.blockpage.memberservice.adaptor.infrastructure.mysql.entity.MemberEntity;
import com.blockpage.memberservice.adaptor.infrastructure.mysql.repository.MemberRepository;
import com.blockpage.memberservice.adaptor.infrastructure.mysql.value.Role;
import com.blockpage.memberservice.application.port.out.port.MemberPort;
import com.blockpage.memberservice.domain.Member;
import com.blockpage.memberservice.exception.CustomException;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class MemberAdaptor implements MemberPort {

    private final MemberRepository memberRepository;
    private final Storage storage;
    private final String bucketName = "blockpage-bucket";

    @Override
    public Member signInMember(Member member) {
        Optional<MemberEntity> memberEntity = memberRepository.findByEmail(member.getEmail());
        if (memberEntity.isPresent()) {
            return Member.afterSignIn(false, memberEntity.get().getRole());
        } else {
            memberRepository.save(MemberEntity.fromMember(member));
            return Member.afterSignIn(true, Role.MEMBER);
        }
    }

    @Override
    public Member findMemberInfo(Member member) {
        MemberEntity memberEntity = memberRepository.findByEmail(member.getEmail())
            .orElseThrow(() -> new CustomException(UNKNOWN_ERROR.getMessage(), UNKNOWN_ERROR.getHttpStatus()));
        return Member.fromMemberEntity(memberEntity);
    }

    @Override
    public Member findNickname(Member member) {
        Optional<MemberEntity> memberEntity = memberRepository.findByCreatorNickname(member.getCreatorNickname());
        if (memberEntity.isPresent()) {
            throw new CustomException(NICKNAME_ALREADY_EXIST.getMessage(), NICKNAME_ALREADY_EXIST.getHttpStatus());
        } else {
            return member;
        }
    }

    @Override
    public void updateMemberInfo(Member member) throws IOException {
        MemberEntity memberEntity = memberRepository.findByEmail(member.getEmail()).get();
        if (member.getType().equals("member")) {
            if (member.getNewProfileImage() != null) {
                String profileImageUUID = UUID.randomUUID().toString();
                BlobId blobId = BlobId.of(bucketName, profileImageUUID);
                BlobInfo blobInfo = BlobInfo.newBuilder(blobId)
                    .setContentType(member.getNewProfileImage().getContentType())
                    .build();
                storage.create(blobInfo, member.getNewProfileImage().getBytes());
                Member updateMember = Member.builder()
                    .email(member.getEmail())
                    .nickname(member.getNickname())
                    .profileImage("https://storage.googleapis.com/blockpage-bucket/" + profileImageUUID)
                    .adult(member.getAdult())
                    .build();
                memberRepository.save(MemberEntity.updateMember(memberEntity, updateMember));
            } else {
                Member updateMember = Member.builder()
                    .email(member.getEmail())
                    .profileImage(member.getProfileImage())
                    .nickname(member.getNickname())
                    .build();
                memberRepository.save(MemberEntity.updateMember(memberEntity, updateMember));
            }
        } else if (member.getType().equals("author")) {
            memberEntity.setRole(Role.AUTHOR);
            memberEntity.setCreatorNickname(member.getCreatorNickname());
            memberRepository.save(memberEntity);
        } else if (member.getType().equals("profileSkin")) {
            memberEntity.setProfileSkin(member.getProfileSkin());
            memberRepository.save(memberEntity);
        } else {
            throw new CustomException(UNKNOWN_ERROR.getMessage(), UNKNOWN_ERROR.getHttpStatus());
        }
    }
}
