package com.beauty4u.backend.inquiry.query.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class InquiryDetailResDTO {

    private String inquiryId;
    private String userCode;
    private String userName;
    private String inquiryTitle;
    private String inquiryContent;
    private String inquiryViewcount;
    private String publishStatus;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private String inquirySecretYn;
    private String inquiryReplyYn;
}
