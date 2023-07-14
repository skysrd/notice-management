package com.skysrd.noticemanagement.api.notice.service;

import com.skysrd.noticemanagement.api.notice.domain.request.CreateNoticeRequest;
import com.skysrd.noticemanagement.api.notice.domain.request.DeleteNoticeRequest;
import com.skysrd.noticemanagement.api.notice.domain.request.UpdateNoticeRequest;
import com.skysrd.noticemanagement.api.notice.domain.response.NoticeResponse;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public interface NoticeService {
    public UUID createNotice(CreateNoticeRequest createNoticeRequest) throws IOException;
    public List<NoticeResponse> getNoticeList();
    public NoticeResponse getNoticeDetail(UUID noticeId);
    public UUID updateNotice(UpdateNoticeRequest updateNoticeRequest);
    public void deleteNotice(DeleteNoticeRequest deleteNoticeRequest);
}
