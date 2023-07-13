package com.skysrd.noticemanagement.api.notice.controller;

import com.skysrd.noticemanagement.api.notice.domain.request.CreateNoticeRequest;
import com.skysrd.noticemanagement.api.notice.domain.request.DeleteNoticeRequest;
import com.skysrd.noticemanagement.api.notice.domain.request.UpdateNoticeRequest;
import com.skysrd.noticemanagement.api.notice.service.AdminNoticeServiceImpl;
import com.skysrd.noticemanagement.common.result.ResponseHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/notice")
@RequiredArgsConstructor
public class NoticeController {
    private final AdminNoticeServiceImpl adminNoticeServiceImpl;

    @PostMapping()
    public ResponseEntity<?> createNotice(@RequestBody CreateNoticeRequest createNoticeRequest) {
        return ResponseHandler.generate()
                .status(HttpStatus.CREATED)
                .data(adminNoticeServiceImpl.createNotice(createNoticeRequest))
                .build();
    }

    @GetMapping()
    public ResponseEntity<?> getNoticeList() {
        return ResponseHandler.generate()
                .status(HttpStatus.OK)
                .data(adminNoticeServiceImpl.getNoticeList())
                .build();
    }

    @GetMapping("/detail")
    public ResponseEntity<?> getNoticeDetail(UUID noticeId) {
        return  ResponseHandler.generate()
                .status(HttpStatus.OK)
                .data(adminNoticeServiceImpl.getNoticeDetail(noticeId))
                .build();
    }

    @PatchMapping()
    public ResponseEntity<?> updateNotice(@RequestBody UpdateNoticeRequest updateNoticeRequest) {
        return ResponseHandler.generate()
                .status(HttpStatus.OK)
                .data(adminNoticeServiceImpl.updateNotice(updateNoticeRequest))
                .build();
    }

    @DeleteMapping()
    public ResponseEntity<?> deleteNotice(@RequestBody DeleteNoticeRequest deleteNoticeRequest) {
        adminNoticeServiceImpl.deleteNotice(deleteNoticeRequest);

        return ResponseHandler.generate()
                .status(HttpStatus.OK)
                .data(null)
                .build();
    }
}
