package com.blockpage.memberservice.adaptor.infrastructure.persistance;

import com.blockpage.memberservice.adaptor.infrastructure.entity.MemberEntity;
import com.blockpage.memberservice.adaptor.infrastructure.repository.MemberRepository;
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
    private String bucketName = "blockpage-bucket";

    @Override
    public Member findMember(Member member) {
        Optional<MemberEntity> memberEntity = memberRepository.findByKakaoId(member.getKakaoId());
        if (memberEntity.isPresent()) {
            return Member.fromMemberEntity(memberEntity.get());
        }
        return null;
    }

    @Override
    public void saveMember(Member member) {
        memberRepository.save(MemberEntity.fromMember(member));
    }

    @Override
    public Member findMemberInfo(Member member) {
        Optional<MemberEntity> memberEntity = memberRepository.findById(member.getId());
        if (memberEntity.isPresent()) {
            Member member1 = Member.fromMemberEntity(memberEntity.get());
            return member1;
        }
        return null;
    }

    @Override
    public void updateMemberInfo(@RequestBody Member member) throws IOException {
        String profileImageUUID = UUID.randomUUID().toString();
        BlobId blobId = BlobId.of(bucketName,profileImageUUID);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId)
            .setContentType(member.getNewProfileImage().getContentType())
            .build();
        storage.create(blobInfo,member.getNewProfileImage().getBytes());
        Member updateMember = Member.builder()
            .id(member.getId())
            .nickname(member.getNickname())
            .profileImage(profileImageUUID)
            .adult(member.getAdult())
            .build();
        Optional<MemberEntity> memberEntity = memberRepository.findById(member.getId());
        memberRepository.save(MemberEntity.updateMember(memberEntity.get(), updateMember));
    }

    @Override
    public Member updateCreatorNickname(Member member) {
        Optional<MemberEntity> memberEntity = memberRepository.findByCreatorNickname(member.getCreatorNickname());
        if (memberEntity.isPresent()) {
            throw new RuntimeException("중복된 닉네임입니다.");
        } else {
            return member;
        }
    }

    @Override
    public void updateMemberRole(Member member) {
        Optional<MemberEntity> memberEntity = memberRepository.findById(member.getId());
        memberRepository.save(MemberEntity.updateMember(memberEntity.get(), member));
    }
}
