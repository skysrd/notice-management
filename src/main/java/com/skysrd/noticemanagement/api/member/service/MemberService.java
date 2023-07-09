package com.skysrd.noticemanagement.api.member.service;

import com.skysrd.noticemanagement.api.member.domain.entity.Member;
import com.skysrd.noticemanagement.api.member.domain.request.MemberSignupRequest;
import com.skysrd.noticemanagement.api.member.domain.response.MemberSignupResponse;
import com.skysrd.noticemanagement.api.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;

@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberSignupResponse signup(MemberSignupRequest memberSignupRequest) {
        Member member = memberSignupRequest.toMember();
        return MemberSignupResponse.toResponse(member);
    }
}
