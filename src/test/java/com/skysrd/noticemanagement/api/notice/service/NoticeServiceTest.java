package com.skysrd.noticemanagement.api.notice.service;

import com.skysrd.noticemanagement.api.notice.domain.entity.Notice;
import com.skysrd.noticemanagement.api.notice.domain.request.CreateNoticeRequest;
import com.skysrd.noticemanagement.api.notice.domain.response.NoticeResponse;
import com.skysrd.noticemanagement.api.notice.repository.NoticeRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.UUID;

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
        Assertions.assertThat(noticeId).isEqualTo(notice.getId());
        Assertions.assertThat(createNoticeRequest.getTitle()).isEqualTo(notice.getTitle());
        Assertions.assertThat(createNoticeRequest.getContent()).isEqualTo(notice.getContent());
        Assertions.assertThat(createNoticeRequest.getStartDate()).isEqualTo(notice.getStartDate());
        Assertions.assertThat(createNoticeRequest.getEndDate()).isEqualTo(notice.getEndDate());
    }

    @Test
    void getNoticeList() {
    }

    @Test
    void getNoticeDetail() {
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
        Assertions.assertThat(noticeResponse.getId()).isEqualTo(notice.getId());
        Assertions.assertThat(noticeResponse.getTitle()).isEqualTo(notice.getTitle());
        Assertions.assertThat(noticeResponse.getContent()).isEqualTo(notice.getContent());
        Assertions.assertThat(noticeResponse.getStartDate()).isEqualTo(notice.getStartDate());
        Assertions.assertThat(noticeResponse.getEndDate()).isEqualTo(notice.getEndDate());
        Assertions.assertThat(noticeResponse.getReadCount()).isEqualTo(notice.getReadCount()+1); //조회하면서 발생한 reflect와의 차이 보정
    }

    @Test
    void updateNotice() {
    }

    @Test
    void deleteNotice() {
    }
}