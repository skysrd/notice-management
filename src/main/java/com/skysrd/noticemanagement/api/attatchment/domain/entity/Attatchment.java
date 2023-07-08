package com.skysrd.noticemanagement.api.attatchment.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;

import java.util.UUID;

@Table(name = "attatchments")
@Entity
public class Attatchment {
    //첨부파일

    @Id @Column(name = "attatchment_id")
    private UUID id; //첨부파일 아이디

    @Column(name = "caller_id")
    private UUID callerId; //호출자 아이디

    @Column(name = "attatchment_path")
    private String path; //파일 저장 경로

    @Builder(builderClassName = "createBuilder", builderMethodName = "createBuilder")
    public Attatchment(UUID callerId, String path) {
        this.id = UUID.randomUUID();
        this.callerId = callerId;
        this.path = path;
    }
}
