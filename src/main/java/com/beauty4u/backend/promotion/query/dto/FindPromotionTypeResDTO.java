package com.beauty4u.backend.promotion.query.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/* 프로모션 종류 조회 DTO */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FindPromotionTypeResDTO {
    private Integer promotionTypeId;
    private String promotionTypeName;
}
