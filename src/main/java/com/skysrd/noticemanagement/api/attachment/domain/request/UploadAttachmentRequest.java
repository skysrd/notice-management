package com.skysrd.noticemanagement.api.attachment.domain.request;

import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Getter
public class UploadAttachmentRequest {
    private UUID callerId;
    private List<MultipartFile> files;

    @Builder
    public UploadAttachmentRequest(UUID callerId, List<MultipartFile> files) {
        this.callerId = callerId;
        this.files = files;
    }
}
