package com.skysrd.noticemanagement.api.member.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@AllArgsConstructor
public enum MemberErrorCode {
    NO_PERMISSION("NO_PERMISSION", "권한이 없습니다.");
    private String code;
    private String text;
}
