package com.beauty4u.backend.inquiry.query.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InquiryFilterReqDTO {

    private String qnaTitle;
    private String publishStatus;
    private String sort;
    private String order;
    private Long page;
    private Long count;
}