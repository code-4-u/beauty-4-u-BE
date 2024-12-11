package com.beauty4u.backend.analysis.query.dto.response;

import lombok.Getter;
import lombok.Setter;

/* 연령대 별 브랜드 구매 횟수 응답 DTO */
@Getter
@Setter
public class AnalysisPurchasesByBrandResDTO {
    private String brandCode;    /* 브랜드 코드 */
    private String brandName;    /* 브랜드 이름 */
    private Integer orderCount;  /* 구매 횟수 */
}
