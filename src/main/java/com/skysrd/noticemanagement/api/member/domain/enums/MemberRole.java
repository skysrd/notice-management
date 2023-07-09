package com.skysrd.noticemanagement.api.member.domain.enums;

import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;

@AllArgsConstructor
public enum MemberRole {
    ROLE_ADMIN("ROLE_ADMIN", "관리자"),
    ROLE_USER("ROLE_USER", "사용자");

    private final String code;
    private final String text;
}
