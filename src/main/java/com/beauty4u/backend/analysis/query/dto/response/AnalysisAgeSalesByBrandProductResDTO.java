package com.beauty4u.backend.analysis.query.dto.response;

import lombok.Getter;
import lombok.Setter;

/* 연령별 브랜드 제품별 매출 조회 (막대 그래프) 응답 DTO */
@Getter
@Setter
public class AnalysisAgeSalesByBrandProductResDTO {
    private String goodsName;       /* 제품명 */
    private Integer totalBuyPrice;  /* 총 매출 */
}
