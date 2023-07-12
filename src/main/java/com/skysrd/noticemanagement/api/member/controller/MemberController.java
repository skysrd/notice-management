package com.skysrd.noticemanagement.api.member.controller;

import com.skysrd.noticemanagement.api.member.domain.request.MemberSignupRequest;
import com.skysrd.noticemanagement.api.member.service.MemberService;
import com.skysrd.noticemanagement.common.result.ResponseHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberController {
    //사용자 관리 Controller
    private final MemberService memberService;

    //사용자 추가
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody MemberSignupRequest memberSignupRequest) {
        return ResponseHandler.generate()
                .status(HttpStatus.CREATED)
                .data(memberService.signup(memberSignupRequest))
                .build();
    }
}
