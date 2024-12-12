package com.beauty4u.backend.analysis.query.dto.response;


import lombok.Getter;
import lombok.Setter;

/* 연령별 매출 비율 조회 (원형 그래프) */
@Getter
@Setter
public class AnalysisAgeSalesRadioResDTO {
    private Integer totalSales;         /* 총 매출 */

    private Integer tenSales;           /* 10대 매출 */
    private Integer twentySales;        /* 20대 매출 */
    private Integer thirtySales;        /* 30대 매출 */
    private Integer fortySales;         /* 40대 매출 */
    private Integer fiftySales;         /* 50대 매출 */
    private Integer sixtySales;         /* 60대 이상 매출 */

    private Double tenSalesRadio;       /* 10대 매출 비율 */
    private Double twentySalesRadio;    /* 20대 매출 비율 */
    private Double thirtySalesRadio;    /* 30대 매출 비율 */
    private Double fortySalesRadio;     /* 40대 매출 비율 */
    private Double fiftySalesRadio;     /* 50대 매출 비율 */
    private Double sixtySalesRadio;     /* 60대 이상 매출 비율*/
}
