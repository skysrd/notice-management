package com.skysrd.noticemanagement.api.notice.domain.request;

import com.skysrd.noticemanagement.api.notice.domain.entity.Notice;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CreateNoticeRequest {
    private String title;
    private String content;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public static Notice toEntity(CreateNoticeRequest createNoticeRequest) {
        return Notice.createBuilder()
                .title(createNoticeRequest.getTitle())
                .content(createNoticeRequest.getContent())
                .startDate(createNoticeRequest.getStartDate())
                .endDate(createNoticeRequest.getEndDate())
                .build();
    }
}
