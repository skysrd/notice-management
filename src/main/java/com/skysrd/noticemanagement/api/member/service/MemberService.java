package com.skysrd.noticemanagement.api.member.service;

import com.skysrd.noticemanagement.api.member.domain.entity.Member;
import com.skysrd.noticemanagement.api.member.domain.request.MemberSignupRequest;
import com.skysrd.noticemanagement.api.member.domain.response.MemberResponse;
import com.skysrd.noticemanagement.api.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
    //사용자 관리 서비스

    private final MemberRepository memberRepository;

    //사용자 추가 기능
    @Transactional
    public MemberResponse signup(MemberSignupRequest memberSignupRequest) {
        //사용자 인스턴스 생성
        Member member = MemberSignupRequest.toMember(memberSignupRequest);
        //사용자 추가
        memberRepository.save(member);
        //사용자 응답 반환
        return MemberResponse.toResponse(member);
    }
}
