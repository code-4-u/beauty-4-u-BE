package com.beauty4u.backend.promotion.query.dto;

import lombok.*;

/* 프로모션 기간 상품 매출 랭킹 비교 DTO */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FindPromotionByComparisonSalesResDTO {
    private String goodsName;           /* 제품명 */
    private Integer salesYearPrevious;  /* 이전년도 제품 매출 순 */
    private Integer salesYearAfter;     /* 이후년도 제품 매출 순 */
    private Integer salesRankPrevious;  /* 이전년도 랭킹 */
    private Integer salesRankAfter;     /* 이후년도 행킹 */
    private String rankChange;          /* 랭킹 변경 */
    private String salesPercentChange;  /* 이전년도 대비 증감률 */
}
