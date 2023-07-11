package com.skysrd.noticemanagement.api.attachment.service;

import com.skysrd.noticemanagement.common.component.S3Upload;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class AttachmentService {
    private final S3Upload s3Upload;

    public String upload(MultipartFile multipartFile) throws IOException {
        return s3Upload.upload(multipartFile);
    }
}
