package com.skysrd.noticemanagement.api.attachment.repository;

import com.skysrd.noticemanagement.api.attachment.domain.entity.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AttatchmentRepository extends JpaRepository<Attachment, UUID> {
}
