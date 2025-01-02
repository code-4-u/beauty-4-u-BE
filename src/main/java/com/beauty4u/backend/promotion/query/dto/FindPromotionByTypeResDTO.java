package com.beauty4u.backend.promotion.query.dto;

import lombok.*;

/* 프로모션 타입별 조회 ASC 조회 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FindPromotionByTypeResDTO {
    private Integer promotionId;     /* 프로모션 ID */
    private Integer promotionTypeId; /* 프로모션 타입 ID */
    private String promotionTitle;   /* 프로모션 제목 */
}
