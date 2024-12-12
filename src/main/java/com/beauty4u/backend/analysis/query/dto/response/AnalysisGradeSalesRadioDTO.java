package com.beauty4u.backend.analysis.query.dto.response;

import lombok.Getter;
import lombok.Setter;

/* 등급별 매출 비율 (원형그래프) 조회 */
@Getter
@Setter
public class AnalysisGradeSalesRadioDTO {
    private Integer totalSales;     /* 총 매출 */
    private Integer goldSales;      /* 골드 등급 매출 */
    private Integer blackSales;     /* 블랙 등급 매출 */
    private Integer greenSales;     /* 그린 등급 매출 */
    private Integer pinkSales;      /* 핑크 등급 매출 */
    private Integer babySales;      /* 베이비 등급 매출 */

    private Double goldSalesRadio;  /* 골드 등급 매출 비율 */
    private Double blackSalesRadio; /* 블랙 등급 매출 비율 */
    private Double greenSalesRadio; /* 그린 등급 매출 비율 */
    private Double pinkSalesRadio;  /* 핑크 등급 매출 비율 */
    private Double babySalesRadio;  /* 베이비 등급 매출 비율 */
}
