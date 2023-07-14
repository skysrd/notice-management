package com.skysrd.noticemanagement.api.notice.domain.response;

import com.skysrd.noticemanagement.api.notice.domain.entity.Notice;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class NoticeResponse {
    private String title;
    private String content;
    private LocalDateTime createDate;
    private Integer readCount;
    private UUID createdBy;

    @Builder(builderClassName = "createBuilder", builderMethodName = "createBuilder")
    public NoticeResponse(String title, String content, LocalDateTime createDate, Integer readCount, UUID createdBy) {
        this.title = title;
        this.content = content;
        this.createDate = createDate;
        this.readCount = readCount;
        this.createdBy = createdBy;
    }

    public static NoticeResponse toResponse(Notice notice) {
        return NoticeResponse.createBuilder()
                .title(notice.getTitle())
                .content(notice.getContent())
                .createDate(notice.getCreatedDate())
                .readCount(notice.getReadCount())
                .createdBy(notice.getCreatedBy())
                .build();
    }
}
