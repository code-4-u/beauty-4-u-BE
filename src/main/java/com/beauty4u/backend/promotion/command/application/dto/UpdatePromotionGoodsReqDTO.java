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
public class UpdatePromotionGoodsReqDTO {

    List<UpdatePromotionGoodsListReqDTO> promotionGoodsList;
}
