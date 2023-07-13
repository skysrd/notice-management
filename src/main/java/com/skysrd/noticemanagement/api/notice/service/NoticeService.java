package com.skysrd.noticemanagement.api.notice.service;

import com.skysrd.noticemanagement.api.notice.domain.entity.Notice;
import com.skysrd.noticemanagement.api.notice.domain.enums.NoticeErrorCode;
import com.skysrd.noticemanagement.api.notice.domain.request.CreateNoticeRequest;
import com.skysrd.noticemanagement.api.notice.domain.request.DeleteNoticeRequest;
import com.skysrd.noticemanagement.api.notice.domain.request.UpdateNoticeRequest;
import com.skysrd.noticemanagement.api.notice.domain.response.NoticeResponse;
import com.skysrd.noticemanagement.common.exception.ApiException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public interface NoticeService {
    public UUID createNotice(CreateNoticeRequest createNoticeRequest);
    public List<NoticeResponse> getNoticeList();
    public NoticeResponse getNoticeDetail(UUID noticeId);
    public NoticeResponse updateNotice(UpdateNoticeRequest updateNoticeRequest);
    public void deleteNotice(DeleteNoticeRequest deleteNoticeRequest);
}
