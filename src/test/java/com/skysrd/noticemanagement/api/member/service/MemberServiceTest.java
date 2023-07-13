package com.skysrd.noticemanagement.api.member.service;

import com.skysrd.noticemanagement.api.member.domain.entity.Member;
import com.skysrd.noticemanagement.api.member.domain.enums.MemberRole;
import com.skysrd.noticemanagement.api.member.domain.request.MemberSignupRequest;
import com.skysrd.noticemanagement.api.member.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
@ExtendWith({MockitoExtension.class})
class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    void 관리자회원가입() {
        //given
        MemberSignupRequest memberSignupRequest = MemberSignupRequest.builder()
                .memberRole(MemberRole.ROLE_ADMIN)
                .build();

        //when
        UUID memberId = memberService.signup(memberSignupRequest).getMemberId();

        //then
        Member findMember = memberRepository.findById(memberId).get();
        Assertions.assertThat(memberId).isEqualTo(findMember.getId());
    }

    @Test
    void 사용자회원가입() {
        //given
        MemberSignupRequest memberSignupRequest = MemberSignupRequest.builder()
                .memberRole(MemberRole.ROLE_USER)
                .build();

        //when
        UUID memberId = memberService.signup(memberSignupRequest).getMemberId();

        //then
        Member findMember = memberRepository.findById(memberId).get();
        Assertions.assertThat(memberId).isEqualTo(findMember.getId());
    }
}