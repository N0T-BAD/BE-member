package com.blockpage.memberservice.adaptor.infrastructure.persistance;

import com.blockpage.memberservice.adaptor.infrastructure.entity.MemberEntity;
import com.blockpage.memberservice.adaptor.infrastructure.repository.MemberRepository;
import com.blockpage.memberservice.adaptor.infrastructure.value.Role;
import com.blockpage.memberservice.application.port.out.MemberPort;
import com.blockpage.memberservice.domain.Member;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

@Component
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
        if (member.getCreatorNickname() != null) {
            Optional<MemberEntity> memberEntity = memberRepository.findByCreatorNickname(member.getCreatorNickname());
            if (memberEntity.isPresent()) {
                throw new RuntimeException("중복된 닉네임입니다.");
            } else {
                return member;
            }
        } else {
            return Member.fromMemberEntity(memberRepository.findByEmail(member.getEmail()).get());
        }
    }

    @Override
    public void updateMemberInfo(@RequestBody Member member) throws IOException {
        String profileImageUUID = UUID.randomUUID().toString();
        BlobId blobId = BlobId.of(bucketName, profileImageUUID);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId)
            .setContentType(member.getNewProfileImage().getContentType())
            .build();
        storage.create(blobInfo, member.getNewProfileImage().getBytes());
        Member updateMember = Member.builder()
            .email(member.getEmail())
            .nickname(member.getNickname())
            .profileImage(profileImageUUID)
            .adult(member.getAdult())
            .build();
        Optional<MemberEntity> memberEntity = memberRepository.findByEmail(updateMember.getEmail());
        memberRepository.save(MemberEntity.updateMember(memberEntity.get(), updateMember));
    }

    @Override
    public void updateMemberRole(Member member) {
        Optional<MemberEntity> memberEntity = memberRepository.findByEmail(member.getEmail());
        memberRepository.save(MemberEntity.updateMember(memberEntity.get(), member));
    }
}
