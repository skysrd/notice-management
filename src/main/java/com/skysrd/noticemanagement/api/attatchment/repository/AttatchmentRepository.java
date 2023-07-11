package com.skysrd.noticemanagement.api.attatchment.repository;

import com.skysrd.noticemanagement.api.attatchment.domain.entity.Attatchment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AttatchmentRepository extends JpaRepository<Attatchment, UUID> {
}