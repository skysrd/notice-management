package com.skysrd.noticemanagement.api.notice.service.Impl;

import com.skysrd.noticemanagement.api.attachment.domain.request.UploadAttachmentRequest;
import com.skysrd.noticemanagement.api.attachment.service.AttachmentService;
import com.skysrd.noticemanagement.api.member.domain.enums.MemberErrorCode;
import com.skysrd.noticemanagement.api.member.service.MemberService;
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

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminNoticeServiceImpl implements NoticeService {
    private final NoticeRepository noticeRepository;
    private final AttachmentService attachmentService;
    private final MemberService memberService;

    public UUID createNotice(CreateNoticeRequest createNoticeRequest) throws IOException {
        Notice notice = CreateNoticeRequest.toEntity(createNoticeRequest);
        UUID noticeId = noticeRepository.save(notice).getId();

        //사용자가 존재하지 않는 경우 에러 반환
        if(!memberService.isMemberExist(createNoticeRequest.getCreatorId())) {
            throw ApiException.builder()
                    .status(HttpStatus.BAD_REQUEST)
                    .errorMessage(MemberErrorCode.NOT_FOUND.getText())
                    .errorCode(MemberErrorCode.NOT_FOUND.getCode())
                    .build();
        }

        //파일 존재 시 파일 업로드 처리
        if (createNoticeRequest.getFileList() != null) {
            UploadAttachmentRequest uploadAttachmentRequest = UploadAttachmentRequest.builder()
                    .files(createNoticeRequest.getFileList())
                    .callerId(noticeId)
                    .build();

            attachmentService.uploadAttachment(uploadAttachmentRequest);
        }

        return noticeId;
    }

    public List<NoticeResponse> getNoticeList() {
        return noticeRepository.findAllByStartDateBeforeAndEndDateAfter(LocalDateTime.now(), LocalDateTime.now())
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
