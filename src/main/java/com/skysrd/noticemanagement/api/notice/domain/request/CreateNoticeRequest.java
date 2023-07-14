package com.skysrd.noticemanagement.api.notice.domain.request;

import com.skysrd.noticemanagement.api.notice.domain.entity.Notice;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
public class CreateNoticeRequest {
    private String title;
    private String content;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private List<MultipartFile> fileList;

    @Builder(builderClassName = "createBuilder", builderMethodName = "createBuilder")
    public CreateNoticeRequest(String title, String content, LocalDateTime startDate, LocalDateTime endDate, List<MultipartFile> fileList) {
        this.title = title;
        this.content = content;
        this.startDate = startDate;
        this.endDate = endDate;
        this.fileList = fileList;
    }

    public static Notice toEntity(CreateNoticeRequest createNoticeRequest) {
        return Notice.createBuilder()
                .title(createNoticeRequest.getTitle())
                .content(createNoticeRequest.getContent())
                .startDate(createNoticeRequest.getStartDate())
                .endDate(createNoticeRequest.getEndDate())
                .build();
    }
}
