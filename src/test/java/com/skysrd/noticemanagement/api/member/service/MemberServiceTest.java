package com.skysrd.noticemanagement.api.member.service;

import com.skysrd.noticemanagement.api.member.domain.entity.Member;
import com.skysrd.noticemanagement.api.member.domain.enums.MemberRole;
import com.skysrd.noticemanagement.api.member.domain.request.MemberSignupRequest;
import com.skysrd.noticemanagement.api.member.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.aot.hint.MemberCategory;

import java.util.UUID;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    private MemberService memberService;

    @Mock
    private MemberRepository memberRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
        memberService = new MemberService(memberRepository);
    }

    @Test
    void adminSignupTest() {
        //given
        //추가할 사용자의 역할을 ADMIN으로 설정해 Request 생성
        MemberSignupRequest memberSignupRequest = MemberSignupRequest.builder()
                .memberRole(MemberRole.ROLE_ADMIN).build();

        //when
        //사용자 Instance를 만들고, repository를 통해 save
        Member member = MemberSignupRequest.toMember(memberSignupRequest);
        member = memberRepository.save(member);

        //then
        //처음 만든 member와 repository를 통해 저장된 member가 같은 지 확인
        UUID uuid = member.getId();
        assertThat(member).isEqualTo(memberRepository.findById(uuid));
    }

    @Test
    void userSignupTest() {
        //given
        //추가할 사용자의 역할을 USER로 설정해 Request 생성
        MemberSignupRequest memberSignupRequest = MemberSignupRequest.builder()
                .memberRole(MemberRole.ROLE_USER).build();

        //when
        //사용자 Instance를 만들고, repository를 통해 save
        Member member = MemberSignupRequest.toMember(memberSignupRequest);
        member = memberRepository.save(member);

        //then
        //처음 만든 member와 repository를 통해 저장된 member가 같은 지 확인
        assertThat(member).isEqualTo(memberRepository.findById(member.getId()));
    }
}