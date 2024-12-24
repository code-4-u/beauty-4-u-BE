package com.beauty4u.backend.promotion.query.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FindPromotionByGoodsSalesResDTO {
    private String goodsName;
    private Integer totalGoodsSales;
}
