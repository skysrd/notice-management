package com.skysrd.noticemanagement.api.notice.service;

import com.skysrd.noticemanagement.api.notice.domain.entity.Notice;
import com.skysrd.noticemanagement.api.notice.domain.enums.NoticeErrorCode;
import com.skysrd.noticemanagement.api.notice.domain.request.CreateNoticeRequest;
import com.skysrd.noticemanagement.api.notice.domain.request.DeleteNoticeRequest;
import com.skysrd.noticemanagement.api.notice.domain.request.UpdateNoticeRequest;
import com.skysrd.noticemanagement.api.notice.domain.response.NoticeResponse;
import com.skysrd.noticemanagement.api.notice.repository.NoticeRepository;
import com.skysrd.noticemanagement.common.exception.ApiException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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

    @DisplayName("공지사항 작성 파일없이")
    @Test
    void createNoticeWithoutFile() throws IOException {
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

    @DisplayName("공지사항 작성 파일있음")
    @Test
    void createNoticeWithFile() throws IOException {
        //given
        List<MultipartFile> fileList = new ArrayList<>();
        File file1 = new File("resources/static/testfile1.txt");
        File file2 = new File("resources/static/testfile2.png");

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

    @DisplayName("공지사항 상세조회")
    @Test
    void getNoticeDetail() throws IOException {
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
        assertThat(noticeResponse.getTitle()).isEqualTo(notice.getTitle());
        assertThat(noticeResponse.getContent()).isEqualTo(notice.getContent());
        assertThat(noticeResponse.getCreateDate()).isEqualTo(notice.getCreatedDate());
        assertThat(noticeResponse.getReadCount()).isEqualTo(notice.getReadCount() + 1); //조회하면서 발생한 reflect와의 차이 보정
    }

    @DisplayName("공지사항 수정")
    @Test
    void updateNotice() throws IOException {
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

    @DisplayName("공지사항 삭제")
    @Test
    void deleteNotice() throws IOException {
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