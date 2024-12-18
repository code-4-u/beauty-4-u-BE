package com.beauty4u.backend.promotion.command.application.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SavePromotionGoodsReqDTO {

    @NotNull(message = "프로모션 id는 필수입니다.")
    private Long promotionId;

    @NotNull(message = "상품 id는 필수입니다.")
    @NotEmpty(message = "상품은 1개 이상 담아야합니다.")
    private List<SaveGoodsDiscountDTO> saveGoodsDiscountDTOS;
}
