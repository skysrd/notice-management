package com.skysrd.noticemanagement.api.notice.service;

import com.skysrd.noticemanagement.api.notice.domain.entity.Notice;
import com.skysrd.noticemanagement.api.notice.domain.enums.NoticeErrorCode;
import com.skysrd.noticemanagement.api.notice.domain.request.CreateNoticeRequest;
import com.skysrd.noticemanagement.api.notice.domain.request.DeleteNoticeRequest;
import com.skysrd.noticemanagement.api.notice.domain.request.UpdateNoticeRequest;
import com.skysrd.noticemanagement.api.notice.domain.response.NoticeResponse;
import com.skysrd.noticemanagement.api.notice.repository.NoticeRepository;
import com.skysrd.noticemanagement.common.exception.ApiException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class NoticeServiceTest {
    @Autowired
    private NoticeService noticeService;

    @Autowired
    private NoticeRepository noticeRepository;

    @Test
    void 공지사항_작성_파일없이() {
        //given
        CreateNoticeRequest createNoticeRequest = CreateNoticeRequest.createBuilder()
                .title("title")
                .content("content")
                .startDate(LocalDateTime.now().minusDays(1))
                .endDate(LocalDateTime.now().plusDays(1))
                .build();

        //when
        UUID noticeId = noticeService.createNotice(createNoticeRequest);

        //then
        Notice notice = noticeRepository.findById(noticeId).get();
        assertThat(noticeId).isEqualTo(notice.getId());
        assertThat(createNoticeRequest.getTitle()).isEqualTo(notice.getTitle());
        assertThat(createNoticeRequest.getContent()).isEqualTo(notice.getContent());
        assertThat(createNoticeRequest.getStartDate()).isEqualTo(notice.getStartDate());
        assertThat(createNoticeRequest.getEndDate()).isEqualTo(notice.getEndDate());
    }

    @Test
    void getNoticeList() {
    }

    @Test
    void 공지사항_조회() {
        //given
        CreateNoticeRequest createNoticeRequest = CreateNoticeRequest.createBuilder()
                .title("title")
                .content("content")
                .startDate(LocalDateTime.now().minusDays(1))
                .endDate(LocalDateTime.now().plusDays(1))
                .build();

        //when
        UUID noticeId = noticeService.createNotice(createNoticeRequest);
        NoticeResponse noticeResponse = noticeService.getNoticeDetail(noticeId);

        //then
        Notice notice = noticeRepository.findById(noticeId).get();
        assertThat(noticeResponse.getId()).isEqualTo(notice.getId());
        assertThat(noticeResponse.getTitle()).isEqualTo(notice.getTitle());
        assertThat(noticeResponse.getContent()).isEqualTo(notice.getContent());
        assertThat(noticeResponse.getStartDate()).isEqualTo(notice.getStartDate());
        assertThat(noticeResponse.getEndDate()).isEqualTo(notice.getEndDate());
        assertThat(noticeResponse.getReadCount()).isEqualTo(notice.getReadCount() + 1); //조회하면서 발생한 reflect와의 차이 보정
    }

    @Test
    void 공지사항_수정() {
        //given
        CreateNoticeRequest createNoticeRequest = CreateNoticeRequest.createBuilder()
                .title("title")
                .content("content")
                .startDate(LocalDateTime.now().minusDays(1))
                .endDate(LocalDateTime.now().plusDays(1))
                .build();

        UUID noticeId = noticeService.createNotice(createNoticeRequest);

        //when
        UpdateNoticeRequest updateNoticeRequest = UpdateNoticeRequest.createBuilder()
                .id(noticeId)
                .title("title2")
                .content("content2")
                .startDate(LocalDateTime.now().minusDays(2))
                .endDate(LocalDateTime.now().plusDays(2))
                .build();

        noticeService.updateNotice(updateNoticeRequest);

        //then
        Notice notice = noticeRepository.findById(noticeId).get();
        assertThat(updateNoticeRequest.getId()).isEqualTo(notice.getId());
        assertThat(updateNoticeRequest.getTitle()).isEqualTo(notice.getTitle());
        assertThat(updateNoticeRequest.getContent()).isEqualTo(notice.getContent());
        assertThat(updateNoticeRequest.getStartDate()).isEqualTo(notice.getStartDate());
        assertThat(updateNoticeRequest.getEndDate()).isEqualTo(notice.getEndDate());

    }

    @Test
    void 공지사항_삭제() {
        CreateNoticeRequest createNoticeRequest = CreateNoticeRequest.createBuilder()
                .title("title")
                .content("content")
                .startDate(LocalDateTime.now().minusDays(1))
                .endDate(LocalDateTime.now().plusDays(1))
                .build();

        UUID noticeId = noticeService.createNotice(createNoticeRequest);

        DeleteNoticeRequest deleteNoticeRequest = DeleteNoticeRequest.createBuilder()
                .noticeId(noticeId)
                .build();

        //when
        noticeService.deleteNotice(deleteNoticeRequest);

        //then
        ApiException exception = assertThrows(ApiException.class, () -> noticeService.getNoticeDetail(noticeId));
        assertThat(exception.getStatus()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(exception.getErrorEntity().getError().getCode()).isEqualTo(NoticeErrorCode.NOTICE_NOT_FOUND.getCode());
        assertThat(exception.getErrorEntity().getError().getMessage()).isEqualTo(NoticeErrorCode.NOTICE_NOT_FOUND.getText());
    }
}