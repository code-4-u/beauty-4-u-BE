package com.beauty4u.backend.goods.query.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GoodsQueryDTO {
    private String goodsCode;
    private String goodsName;
    private String brandName;
    private String goodsSkintype;
    private int goodsPrice;
}
