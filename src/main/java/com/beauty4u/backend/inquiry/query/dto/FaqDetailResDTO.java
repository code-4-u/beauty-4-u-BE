package com.beauty4u.backend.inquiry.query.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class FaqDetailResDTO {

    private String faqId;
    private String userCode;
    private String faqTitle;
    private String faqContent;
    private String faqViewcount;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
