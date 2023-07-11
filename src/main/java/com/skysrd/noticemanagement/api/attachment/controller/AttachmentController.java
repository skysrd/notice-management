package com.skysrd.noticemanagement.api.attachment.controller;

import com.amazonaws.Response;
import com.skysrd.noticemanagement.api.attachment.service.AttachmentService;
import com.skysrd.noticemanagement.common.component.S3Upload;
import com.skysrd.noticemanagement.common.result.ResponseHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/attachment")
@RequiredArgsConstructor
public class AttachmentController {

    private final AttachmentService attachmentService;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("images") MultipartFile multipartFile) throws IOException {
        return ResponseHandler.generate()
                .status(HttpStatus.CREATED)
                .data(attachmentService.upload(multipartFile))
                .build();
    }
}
