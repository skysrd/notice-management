package com.skysrd.noticemanagement.api.attachment.service;

import com.skysrd.noticemanagement.api.attachment.domain.entity.Attachment;
import com.skysrd.noticemanagement.api.attachment.domain.request.UploadAttachmentRequest;
import com.skysrd.noticemanagement.api.attachment.repository.AttatchmentRepository;
import com.skysrd.noticemanagement.common.component.S3Upload;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AttachmentService {
    private final S3Upload s3Upload;
    private final AttatchmentRepository attatchmentRepository;

    public Boolean uploadAttachment(UploadAttachmentRequest uploadAttachmentRequest) throws IOException {
        List<MultipartFile> fileList = uploadAttachmentRequest.getFiles();
        List<Attachment> attachmentList = new ArrayList<>();

        for (MultipartFile file : fileList) {
            attachmentList.add(
                    Attachment.createBuilder()
                            .callerId(uploadAttachmentRequest.getCallerId())
                            .path(s3Upload.upload(file))
                            .build()
            );
        }

        attatchmentRepository.saveAll(attachmentList);

        return true;
    }
}
