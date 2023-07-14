package com.skysrd.noticemanagement.api.notice.controller;

import com.skysrd.noticemanagement.api.notice.domain.request.CreateNoticeRequest;
import com.skysrd.noticemanagement.api.notice.domain.request.DeleteNoticeRequest;
import com.skysrd.noticemanagement.api.notice.domain.request.UpdateNoticeRequest;
import com.skysrd.noticemanagement.api.notice.service.NoticeService;
import com.skysrd.noticemanagement.common.result.ResponseHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/notice")
@RequiredArgsConstructor
public class NoticeController {
    private final NoticeService noticeService;

    @PostMapping()
    public ResponseEntity<?> createNotice(@RequestPart CreateNoticeRequest createNoticeRequest,
                                          @RequestPart List<MultipartFile> files) throws IOException {
        createNoticeRequest.setFileList(files);
        return ResponseHandler.generate()
                .status(HttpStatus.CREATED)
                .data(noticeService.createNotice(createNoticeRequest))
                .build();
    }

    @GetMapping()
    public ResponseEntity<?> getNoticeList() {
        return ResponseHandler.generate()
                .status(HttpStatus.OK)
                .data(noticeService.getNoticeList())
                .build();
    }

    @GetMapping("/detail")
    public ResponseEntity<?> getNoticeDetail(@RequestParam UUID noticeId) {
        return  ResponseHandler.generate()
                .status(HttpStatus.OK)
                .data(noticeService.getNoticeDetail(noticeId))
                .build();
    }

    @PatchMapping()
    public ResponseEntity<?> updateNotice(@RequestBody UpdateNoticeRequest updateNoticeRequest) {
        return ResponseHandler.generate()
                .status(HttpStatus.OK)
                .data(noticeService.updateNotice(updateNoticeRequest))
                .build();
    }

    @DeleteMapping()
    public ResponseEntity<?> deleteNotice(@RequestBody DeleteNoticeRequest deleteNoticeRequest) {
        noticeService.deleteNotice(deleteNoticeRequest);

        return ResponseHandler.generate()
                .status(HttpStatus.OK)
                .data(null)
                .build();
    }
}
