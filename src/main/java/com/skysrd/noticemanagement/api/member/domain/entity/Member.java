package com.skysrd.noticemanagement.api.member.domain.entity;

import com.skysrd.noticemanagement.api.member.domain.enums.MemberRole;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@Table(name = "members")
@Entity
@NoArgsConstructor
public class Member {

    @Id @Column(name = "member_id")
    private UUID id;

    @Column(name = "member_role")
    private MemberRole memberRole;

    @Builder(builderClassName = "createBuilder", builderMethodName = "createBuilder")
    public Member( MemberRole memberRole) {
        this.id = UUID.randomUUID();
        this.memberRole = memberRole;
    }
}
