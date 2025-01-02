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
public class UpdatePromotionTypeDTO {

    @NotNull(message = "프로모션 종류 이름은 필수입니다.")
    private String promotionTypeName;
}
