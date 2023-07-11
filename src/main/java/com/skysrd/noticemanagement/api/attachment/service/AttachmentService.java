package com.skysrd.noticemanagement.api.attachment.service;

import com.skysrd.noticemanagement.api.attachment.domain.entity.Attachment;
import com.skysrd.noticemanagement.api.attachment.domain.request.UploadAttachmentRequest;
import com.skysrd.noticemanagement.api.attachment.domain.response.AttachmentResponse;
import com.skysrd.noticemanagement.api.attachment.repository.AttatchmentRepository;
import com.skysrd.noticemanagement.common.component.S3Upload;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class AttachmentService {
    private final S3Upload s3Upload;
    private final AttatchmentRepository attatchmentRepository;

    public AttachmentResponse uploadAttachment(UploadAttachmentRequest uploadAttachmentRequest) throws IOException {
        Attachment attatchment = Attachment.createBuilder()
                .callerId(uploadAttachmentRequest.getCallerId())
                .path(s3Upload.upload(uploadAttachmentRequest.getMultipartFile()))
                .build();

        attatchmentRepository.save(attatchment);

        return AttachmentResponse.toResponse(attatchment);
    }
}
