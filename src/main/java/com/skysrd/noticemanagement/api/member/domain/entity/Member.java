package com.skysrd.noticemanagement.api.member.domain.entity;

import com.skysrd.noticemanagement.api.member.domain.enums.MemberRole;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Table(name = "members")
@Entity
public class Member {

    @Id @Column(name = "member_id")
    private UUID id;

    @Column(name = "member_role")
    private MemberRole memberRole;

    public Member(UUID id, MemberRole memberRole) {
        this.id = id;
        this.memberRole = memberRole;
    }
}
