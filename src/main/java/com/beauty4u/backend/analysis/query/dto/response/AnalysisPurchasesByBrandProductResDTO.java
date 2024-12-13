package com.beauty4u.backend.analysis.query.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/* 해당 브랜드의 제품별 구매 횟수(수평 막대 그래프) 조회 응답 DTO */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnalysisPurchasesByBrandProductResDTO {
    private String goodsName;   /* 제품명 */
    private int totalBuyCount;  /* 총 구매 횟수 */
}
