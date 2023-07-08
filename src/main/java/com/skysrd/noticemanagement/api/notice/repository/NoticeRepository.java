package com.skysrd.noticemanagement.api.notice.repository;

import com.skysrd.noticemanagement.api.notice.domain.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface NoticeRepository extends JpaRepository<Notice, UUID> {
}
