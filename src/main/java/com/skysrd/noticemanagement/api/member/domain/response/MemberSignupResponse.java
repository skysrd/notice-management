package com.skysrd.noticemanagement.api.member.domain.response;

import com.skysrd.noticemanagement.api.member.domain.entity.Member;
import com.skysrd.noticemanagement.api.member.domain.enums.MemberRole;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
public class MemberSignupResponse {
    private UUID memberId;
    private MemberRole memberRole;

    @Builder(builderClassName = "responseBuilder", builderMethodName = "responseBuilder")
    public MemberSignupResponse(UUID memberId, MemberRole memberRole) {
        this.memberId = memberId;
        this.memberRole = memberRole;
    }

    public static MemberSignupResponse toResponse(Member member) {
        return responseBuilder()
                .memberId(member.getId())
                .memberRole(member.getMemberRole())
                .build();
    }
}
