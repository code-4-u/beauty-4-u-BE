package com.beauty4u.backend.analysis.query.dto.response;

import lombok.Getter;
import lombok.Setter;

/* 등급별 브랜드별 제품 매출 조회 (수평 막대 그래프) 응답 DTO */
@Getter
@Setter
public class AnalysisGradeSalesByBrandProductResDTO {
    private String goodsName;
    private Integer totalBuyPrice;
}
