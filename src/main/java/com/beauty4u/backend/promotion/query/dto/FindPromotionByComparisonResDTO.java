package com.beauty4u.backend.promotion.query.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FindPromotionByComparisonResDTO {
    private String goodsName1;
    private Integer prevPromotionRank;
    private Integer afterPromotionRank;
    private String rankChange;
}
