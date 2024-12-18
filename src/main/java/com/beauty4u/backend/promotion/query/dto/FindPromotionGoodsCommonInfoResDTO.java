package com.beauty4u.backend.promotion.query.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FindPromotionGoodsCommonInfoResDTO {

    private Long promotionId;
    private String promotionTitle;
    private LocalDateTime promotionStartDate;
    private LocalDateTime promotionEndDate;
    private Long discountRate;
    private Long sales; // 매출액
    private long avgSales; // 1년간이 평균 매출액
    private Double percent; // 1년간의 평균 매출액 대비 프로모션 매출액 증감율

    public void modify(
            FindPromotionGoodsSalesDTO findPromotionGoodsSalesDTO,
            Long sales,
            long avgSales,
            double percent
    ) {

        this.promotionId = findPromotionGoodsSalesDTO.getPromotionId();
        this.promotionTitle = findPromotionGoodsSalesDTO.getPromotionTitle();
        this.promotionStartDate = findPromotionGoodsSalesDTO.getPromotionStartDate();
        this.promotionEndDate = findPromotionGoodsSalesDTO.getPromotionEndDate();
        this.discountRate = findPromotionGoodsSalesDTO.getDiscountRate();
        this.sales = sales;
        this.avgSales = avgSales;
        this.percent = percent;
    }
}
