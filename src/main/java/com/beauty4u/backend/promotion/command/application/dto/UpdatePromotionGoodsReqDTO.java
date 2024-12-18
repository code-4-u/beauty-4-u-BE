package com.beauty4u.backend.promotion.command.application.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePromotionGoodsReqDTO {

    @NotNull(message = "프로모션 상품 id 입력은 필수입니다.")
    private Long id;

    @NotNull(message = "프르모션 상품 할인율은 null일 수 없습니다.")
    @NotEmpty(message = "프로모션 상품 할인율 입력은 빈 값으로 할 수 없습니다.")
    private Long discountRate;
}
