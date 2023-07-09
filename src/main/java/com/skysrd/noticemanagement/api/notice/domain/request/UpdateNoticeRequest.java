package com.skysrd.noticemanagement.api.notice.domain.request;

import jakarta.persistence.Column;
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
}
