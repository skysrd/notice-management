package com.skysrd.noticemanagement.api.attachment.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Table(name = "attachments")
@Entity
@NoArgsConstructor
@Getter
public class Attachment {
    //첨부파일

    @Id @Column(name = "attachment_id")
    private UUID id; //첨부파일 아이디

    @Column(name = "caller_id")
    private UUID callerId; //호출자 아이디

    @Column(name = "attachment_path")
    private String path; //파일 저장 경로

    @Builder(builderClassName = "createBuilder", builderMethodName = "createBuilder")
    public Attachment(UUID callerId, String path) {
        this.id = UUID.randomUUID();
        this.callerId = callerId;
        this.path = path;
    }
}
