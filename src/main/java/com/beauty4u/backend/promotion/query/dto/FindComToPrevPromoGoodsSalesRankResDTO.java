package com.beauty4u.backend.promotion.query.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FindComToPrevPromoGoodsSalesRankResDTO {
    private Integer promoYear;
    private Integer totalSales;
    private Integer prevYearSales;
    private Integer growthRate;
}
