package com.skysrd.noticemanagement.api.member.domain.request;

import com.skysrd.noticemanagement.api.member.domain.entity.Member;
import com.skysrd.noticemanagement.api.member.domain.enums.MemberRole;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberSignupRequest {
    private MemberRole memberRole;

    public Member toMember() {
        return Member.createBuilder()
                .memberRole(memberRole)
                .build();
    }
}
