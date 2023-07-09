package com.skysrd.noticemanagement.api.member.domain.response;

import com.skysrd.noticemanagement.api.member.domain.entity.Member;
import com.skysrd.noticemanagement.api.member.domain.enums.MemberRole;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
public class MemberResponse {
    //사용자 Id
    private UUID memberId;
    //사용자 역할
    private MemberRole memberRole;

    @Builder(builderClassName = "responseBuilder", builderMethodName = "responseBuilder")
    public MemberResponse(UUID memberId, MemberRole memberRole) {
        this.memberId = memberId;
        this.memberRole = memberRole;
    }

    public static MemberResponse toResponse(Member member) {
        return responseBuilder()
                .memberId(member.getId())
                .memberRole(member.getMemberRole())
                .build();
    }
}
