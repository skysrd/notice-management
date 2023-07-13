package com.skysrd.noticemanagement.api.notice.domain.request;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class UpdateNoticeRequest {
    private UUID id;
    private String title;
    private String content;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    @Builder(builderClassName = "createBuilder", builderMethodName = "createBuilder")
    public UpdateNoticeRequest(UUID id, String title, String content, LocalDateTime startDate, LocalDateTime endDate) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
