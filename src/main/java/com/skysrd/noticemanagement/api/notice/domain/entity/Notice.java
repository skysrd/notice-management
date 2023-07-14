package com.skysrd.noticemanagement.api.notice.domain.entity;

import com.skysrd.noticemanagement.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;

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

    @Lob
    @Column(name = "content")
    private String content;

    @Column(name = "start_time")
    private LocalDateTime startDate;

    @Column(name = "end_time")
    private LocalDateTime endDate;

    @Column(name = "read_count")
    private Integer readCount;

    @CreatedBy
    @Column(name = "created_by", updatable = false)
    private UUID createdBy;

    public Notice(UUID id, String title, String content, LocalDateTime startDate, LocalDateTime endDate, Integer readCount, UUID createdBy) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.startDate = startDate;
        this.endDate = endDate;
        this.readCount = readCount;
        this.createdBy = createdBy;
    }

    @Builder(builderClassName = "createBuilder", builderMethodName = "createBuilder")
    public Notice(String title, String content, LocalDateTime startDate, LocalDateTime endDate, UUID createdBy) {
        this.id = UUID.randomUUID();
        this.title = title;
        this.content = content;
        this.startDate = startDate;
        this.endDate = endDate;
        this.readCount = 0;
        this.createdBy = createdBy;
    }

    @Builder(builderClassName = "updateBuilder", builderMethodName = "updateBuilder")
    public void updateNotice(String title, String content, LocalDateTime startDate, LocalDateTime endDate) {
        this.title = title;
        this.content = content;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public void read() {
        this.readCount++;
    }
}
