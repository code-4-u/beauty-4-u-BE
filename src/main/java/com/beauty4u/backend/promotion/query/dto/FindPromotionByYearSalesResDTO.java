package com.beauty4u.backend.promotion.query.dto;

import lombok.*;

/* 프로모션 종류에 따른 년도별 그래프 조회 DTO*/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FindPromotionByYearSalesResDTO {
    private String promoYear;       /* 프로모션 진행 년도 */
    private Integer totalSales;     /* 총 매출 */
    private Integer prevYearSales;  /* 이전 프로모션 총 매출 */
    private Integer growthRate;     /* 매출 증감율 */
}
