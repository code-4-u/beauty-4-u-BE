package com.beauty4u.backend.goods_rate.query.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GoodsRateQueryDTO {
    private String goodsCode;
    private String goodsName;
    private String brandName;
    private String rateChange;
    private String rateType;
}
