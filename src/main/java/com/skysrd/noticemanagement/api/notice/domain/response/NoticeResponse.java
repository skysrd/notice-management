package com.skysrd.noticemanagement.api.notice.domain.response;

import com.skysrd.noticemanagement.api.notice.domain.entity.Notice;
import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class NoticeResponse {
    private UUID id;
    private String title;
    private String content;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Integer readCount;

    @Builder
    public NoticeResponse(UUID id, String title, String content, LocalDateTime startDate, LocalDateTime endDate, Integer readCount) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.startDate = startDate;
        this.endDate = endDate;
        this.readCount = readCount;
    }

    public static NoticeResponse toResponse(Notice notice) {
        return NoticeResponse.builder()
                .id(notice.getId())
                .title(notice.getTitle())
                .content(notice.getContent())
                .startDate(notice.getStartDate())
                .endDate(notice.getEndDate())
                .readCount(notice.getReadCount())
                .build();
    }
}
