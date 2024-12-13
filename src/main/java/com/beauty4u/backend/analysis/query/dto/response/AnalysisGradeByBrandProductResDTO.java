package com.beauty4u.backend.analysis.query.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/* 등급별 브랜드 별 제품 구매 횟수 조회 응답 DTO */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnalysisGradeByBrandProductResDTO {
    private String goodsName;
    private Integer totalBuyCount;
}
