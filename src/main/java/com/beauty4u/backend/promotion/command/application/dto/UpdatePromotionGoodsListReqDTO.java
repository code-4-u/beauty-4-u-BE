package com.beauty4u.backend.promotion.command.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePromotionGoodsListReqDTO {

    private Long promotionGoodsId;
    private Integer discountRate;
}
