package com.skysrd.noticemanagement.api.notice.service.Impl;

import com.skysrd.noticemanagement.api.notice.domain.entity.Notice;
import com.skysrd.noticemanagement.api.notice.domain.enums.NoticeErrorCode;
import com.skysrd.noticemanagement.api.notice.domain.request.CreateNoticeRequest;
import com.skysrd.noticemanagement.api.notice.domain.request.DeleteNoticeRequest;
import com.skysrd.noticemanagement.api.notice.domain.request.UpdateNoticeRequest;
import com.skysrd.noticemanagement.api.notice.domain.response.NoticeResponse;
import com.skysrd.noticemanagement.api.notice.repository.NoticeRepository;
import com.skysrd.noticemanagement.api.notice.service.NoticeService;
import com.skysrd.noticemanagement.common.exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminNoticeServiceImpl implements NoticeService {
    private final NoticeRepository noticeRepository;

    public UUID createNotice(CreateNoticeRequest createNoticeRequest) {
        Notice notice = CreateNoticeRequest.toEntity(createNoticeRequest);
        return noticeRepository.save(notice).getId();
    }

    public List<NoticeResponse> getNoticeList() {
        return noticeRepository.findAll()
                .stream().map(NoticeResponse::toResponse)
                .collect(Collectors.toList());
    }

    public NoticeResponse getNoticeDetail(UUID noticeId) {
        Notice notice = noticeRepository.findById(noticeId)
                .orElseThrow(() -> ApiException.builder()
                        .status(HttpStatus.NOT_FOUND)
                        .errorCode(NoticeErrorCode.NOTICE_NOT_FOUND.getCode())
                        .errorMessage(NoticeErrorCode.NOTICE_NOT_FOUND.getText())
                        .build());

        notice.read();

        return NoticeResponse.toResponse(notice);
    }

    public UUID updateNotice(UpdateNoticeRequest updateNoticeRequest) {
        Notice notice = noticeRepository.findById(updateNoticeRequest.getId())
                .orElseThrow(() -> ApiException.builder()
                        .status(HttpStatus.NOT_FOUND)
                        .errorCode(NoticeErrorCode.NOTICE_NOT_FOUND.getCode())
                        .errorMessage(NoticeErrorCode.NOTICE_NOT_FOUND.getText())
                        .build());

        notice.updateBuilder()
                .title(updateNoticeRequest.getTitle())
                .content(updateNoticeRequest.getContent())
                .startDate(updateNoticeRequest.getStartDate())
                .endDate(updateNoticeRequest.getEndDate())
                .build();

        noticeRepository.save(notice);

        return notice.getId();
    }

    public void deleteNotice(DeleteNoticeRequest deleteNoticeRequest) {
        Notice notice = noticeRepository.findById(deleteNoticeRequest.getNoticeId())
                .orElseThrow(() -> ApiException.builder()
                        .status(HttpStatus.NOT_FOUND)
                        .errorCode(NoticeErrorCode.NOTICE_NOT_FOUND.getCode())
                        .errorMessage(NoticeErrorCode.NOTICE_NOT_FOUND.getText())
                        .build());

        noticeRepository.delete(notice);
    }
}
