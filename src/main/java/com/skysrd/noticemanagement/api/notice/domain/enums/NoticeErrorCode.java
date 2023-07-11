package com.skysrd.noticemanagement.api.notice.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum NoticeErrorCode {
    NOTICE_NOT_FOUND("NOTICE_NOT_FOUND", "공지사항을 찾을 수 없습니다.");

    private final String code;
    private final String text;
}
