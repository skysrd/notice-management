package com.skysrd.noticemanagement.api.notice.domain.request;

import lombok.Getter;

import java.util.UUID;

@Getter
public class DeleteNoticeRequest {
    private UUID noticeId;
}
