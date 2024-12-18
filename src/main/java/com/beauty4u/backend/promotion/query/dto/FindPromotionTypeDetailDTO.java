package com.beauty4u.backend.promotion.query.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FindPromotionTypeDetailDTO {

    private Long promotionTypeId;
    private String promotionTypeName;
    private List<PromotionDetailResDTO> promotionDetailList;
}
