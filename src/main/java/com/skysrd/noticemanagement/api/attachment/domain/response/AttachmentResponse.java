package com.skysrd.noticemanagement.api.attachment.domain.response;

import com.skysrd.noticemanagement.api.attachment.domain.entity.Attachment;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
public class AttachmentResponse {
    private UUID attachmentId;
    private UUID callerId;
    private String path;

    @Builder(builderClassName = "createBuilder", builderMethodName = "createBuilder")
    public AttachmentResponse(UUID attachmentId, UUID callerId, String path) {
        this.attachmentId = attachmentId;
        this.callerId = callerId;
        this.path = path;
    }

    public static AttachmentResponse toResponse(Attachment attachment) {
        return AttachmentResponse.createBuilder()
                .attachmentId(attachment.getId())
                .callerId(attachment.getCallerId())
                .path(attachment.getPath())
                .build();
    }
}
