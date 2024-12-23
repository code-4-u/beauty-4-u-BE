package com.beauty4u.backend.inquiry.query.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InquiryReplyResDTO {

    private Long inquiryReplyId;
    private String userName;
    private String inquiryReplyContent;
    private String publishStatus;
    private LocalDateTime createdDate;
}
