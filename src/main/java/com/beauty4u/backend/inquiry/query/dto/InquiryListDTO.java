package com.beauty4u.backend.inquiry.query.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InquiryListDTO {

    private String inquiryId;
    private String userCode;
    private String userName;
    private String inquiryTitle;
    private String publishStatus;
    private String inquiryViewcount;
    private String createdDate;
    private String updatedDate;
    private String inquirySecretYn;
    private String inquiryReplyYn;
}
