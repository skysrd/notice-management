package com.skysrd.noticemanagement.api.notice.domain.request;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
public class DeleteNoticeRequest {
    private UUID noticeId;

    @Builder(builderClassName = "createBuilder", builderMethodName = "createBuilder")
    public DeleteNoticeRequest(UUID noticeId) {
        this.noticeId = noticeId;
    }
}
