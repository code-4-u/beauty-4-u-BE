package com.beauty4u.backend.promotion.query.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PromotionTypeFilterDTO {

    private String promotionTypeName;
    private String sort;
    private String order;
    private Long page;
    private Long count;
}