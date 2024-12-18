package com.beauty4u.backend.promotion.query.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FindPromotionGoodsListResDTO {

    private Long promotionGoodsId;
    private Long promotionId;
    private String goodsCode;
    private String goodsName;
    private Integer discountRate;
}
