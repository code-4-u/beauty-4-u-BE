package com.beauty4u.backend.promotion.command.application.dto;

import com.beauty4u.backend.goods.command.domain.aggregate.Goods;
import com.beauty4u.backend.promotion.command.domain.aggregate.Promotion;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SavePromotionGoodsDTO {

    private Promotion promotion;
    private Goods goodsCode;
    private Integer discountRate;
}
