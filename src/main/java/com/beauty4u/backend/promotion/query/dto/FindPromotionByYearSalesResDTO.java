package com.beauty4u.backend.promotion.query.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/* 프로모션 종류에 따른 년도별 그래프 조회 DTO*/
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FindPromotionByYearSalesResDTO {
    private String promotionTitle;  /* 프로모션 이름 */
    private Integer promoYear;      /* 프로모션 진행한 년도 */
    private Integer totalSales;     /* 총 매출 */
}