package com.skysrd.noticemanagement.api.attachment.domain.request;

import com.skysrd.noticemanagement.api.attachment.domain.entity.Attachment;
import com.skysrd.noticemanagement.common.component.S3Upload;
import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Getter
public class UploadAttachmentRequest {
    private UUID callerId;
    private MultipartFile multipartFile;

    @Builder
    public UploadAttachmentRequest(UUID callerId, MultipartFile multipartFile) {
        this.callerId = callerId;
        this.multipartFile = multipartFile;
    }
}
