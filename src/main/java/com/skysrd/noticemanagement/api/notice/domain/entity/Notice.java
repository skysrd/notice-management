package com.skysrd.noticemanagement.api.notice.domain.entity;

import com.skysrd.noticemanagement.api.notice.domain.request.UpdateNoticeRequest;
import com.skysrd.noticemanagement.common.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Entity
@Table(name = "notices")
@NoArgsConstructor
public class Notice extends BaseEntity {

    @Id @Column(name = "notice_id")
    private UUID id;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "start_time")
    private LocalDateTime startDate;

    @Column(name = "end_time")
    private LocalDateTime endDate;

    @Column(name = "read_count")
    private Integer readCount;

    public Notice(String title, String content, LocalDateTime startDate, LocalDateTime endDate, Integer readCount) {
        this.id = UUID.randomUUID();
        this.title = title;
        this.content = content;
        this.startDate = startDate;
        this.endDate = endDate;
        this.readCount = readCount;
    }

    @Builder(builderClassName = "createBuilder", builderMethodName = "createBuilder")
    public Notice(String title, String content, LocalDateTime startDate, LocalDateTime endDate) {
        this.id = UUID.randomUUID();
        this.title = title;
        this.content = content;
        this.startDate = startDate;
        this.endDate = endDate;
        this.readCount = 0;
    }

    @Builder(builderClassName = "updateBuilder", builderMethodName = "updateBuilder")
    public void updateNotice(UpdateNoticeRequest updateNoticeRequest) {
        this.title = updateNoticeRequest.getTitle();
        this.content = updateNoticeRequest.getContent();
        this.startDate = updateNoticeRequest.getStartDate();
        this.endDate = updateNoticeRequest.getEndDate();
    }

    public void read() {
        this.readCount++;
    }
}
