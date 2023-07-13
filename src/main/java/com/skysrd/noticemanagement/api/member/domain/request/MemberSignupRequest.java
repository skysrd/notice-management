package com.skysrd.noticemanagement.api.member.domain.request;

import com.skysrd.noticemanagement.api.member.domain.entity.Member;
import com.skysrd.noticemanagement.api.member.domain.enums.MemberRole;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberSignupRequest {
    //사용자 권한
    private MemberRole memberRole;

    @Builder
    public MemberSignupRequest(MemberRole memberRole) {
        this.memberRole = memberRole;
    }

    public static Member toEntity(MemberSignupRequest memberSignupRequest) {
        return Member.createBuilder()
                .memberRole(memberSignupRequest.getMemberRole())
                .build();
    }
}
