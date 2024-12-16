package com.beauty4u.backend.inquiry.query.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FaqFilterReqDTO {

    private String faqTitle;
    private Long page = 1L;
    private Long count = 10L;
}