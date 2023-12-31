package com.skysrd.noticemanagement.api.member.repository;

import com.skysrd.noticemanagement.api.member.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MemberRepository extends JpaRepository<Member, UUID> {
}
