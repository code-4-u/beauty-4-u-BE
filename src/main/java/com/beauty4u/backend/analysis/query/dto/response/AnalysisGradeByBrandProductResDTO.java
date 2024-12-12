package com.beauty4u.backend.analysis.query.dto.response;

import lombok.Getter;
import lombok.Setter;

/* 등급별 브랜드 별 제품 구매 횟수 조회 응답 DTO */
@Getter
@Setter
public class AnalysisGradeByBrandProductResDTO {
    private String goodsName;
    private Integer totalBuyCount;
}
