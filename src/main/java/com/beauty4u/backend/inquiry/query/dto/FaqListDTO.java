package com.beauty4u.backend.inquiry.query.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FaqListDTO {

    private String faqId;
    private String userCode;
    private String faqTitle;
    private String faqViewcount;
}
