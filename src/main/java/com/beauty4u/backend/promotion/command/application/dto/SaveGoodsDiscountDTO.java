package com.beauty4u.backend.promotion.command.application.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SaveGoodsDiscountDTO {

    @NotNull(message = "상품 code는 필수입니다.")
    private String goodsCode;

    @NotNull(message = "할인율 입력은 필수입니다.")
    private Integer discountRate;
}
