package com.beauty4u.backend.analysis.query.dto.response;

import lombok.Getter;
import lombok.Setter;

/* 등급별 브랜드 매출 조회 (수평 막대 그래프) */
@Getter
@Setter
public class AnalysisGradeSalesByBrandResDTO {
    private String brandCode;        /* 브랜드 코드 */
    private String brandName;        /* 브랜드 이름 */
    private Integer totalOrderPrice; /* 총 매출 */
}