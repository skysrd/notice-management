package com.skysrd.noticemanagement.api.notice.domain.entity;

import com.skysrd.noticemanagement.common.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "notices")
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
}
