package com.beauty4u.backend.analysis.query.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/* 등급별 브랜드별 제품 매출 조회 (수평 막대 그래프) 응답 DTO */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnalysisGradeSalesByBrandProductResDTO {
    private String goodsName;
    private Integer totalBuyPrice;
}
