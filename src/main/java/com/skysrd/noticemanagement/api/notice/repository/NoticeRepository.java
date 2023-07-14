package com.skysrd.noticemanagement.api.notice.repository;

import com.skysrd.noticemanagement.api.notice.domain.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface NoticeRepository extends JpaRepository<Notice, UUID> {
    List<Notice> findAllByStartDateBeforeAndEndDateAfter(LocalDateTime localDateTime, LocalDateTime now);
}
