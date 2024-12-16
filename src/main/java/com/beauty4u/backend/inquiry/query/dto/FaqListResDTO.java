package com.beauty4u.backend.inquiry.query.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FaqListResDTO {

    private Long totalCount;
    private List<FaqListDTO> faqList;
}
