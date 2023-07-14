package com.skysrd.noticemanagement.api.member.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MemberErrorCode {
    NO_PERMISSION("NO_PERMISSION", "권한이 없습니다."),
    NOT_FOUND("NOT_FOUND", "사용자를 찾을 수 없습니다.");
    private String code;
    private String text;
}
