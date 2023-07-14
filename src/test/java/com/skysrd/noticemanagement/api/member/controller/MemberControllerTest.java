package com.skysrd.noticemanagement.api.member.controller;

import com.skysrd.noticemanagement.api.member.domain.enums.MemberRole;
import com.skysrd.noticemanagement.api.member.domain.request.MemberSignupRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class MemberControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void 관리자_회원가입() throws Exception {
        //given
        MemberSignupRequest memberSignupRequest = MemberSignupRequest.builder()
                .memberRole(MemberRole.ROLE_ADMIN)
                .build();

        //when
        String requestJson = objectMapper.writeValueAsString(memberSignupRequest);

        //then
        mockMvc.perform(
                post("/api/member")
                        .contentType(MediaType.APPLICATION_JSON)
                        .contentType(requestJson)
        ).andExpect(
                status().isOk()
        ).andDo(print());
    }

    @Test
    void 사용자_회원가입() throws Exception {
        //given
        MemberSignupRequest memberSignupRequest = MemberSignupRequest.builder()
                .memberRole(MemberRole.ROLE_USER)
                .build();

        //when
        String requestJson = objectMapper.writeValueAsString(memberSignupRequest);

        //then
        mockMvc.perform(
                post("/api/member")
                        .contentType(MediaType.APPLICATION_JSON)
                        .contentType(requestJson)
        ).andExpect(
                status().isOk()
        ).andDo(print());
    }
}