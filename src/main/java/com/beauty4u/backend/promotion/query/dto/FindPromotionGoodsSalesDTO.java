package com.beauty4u.backend.promotion.query.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FindPromotionGoodsSalesDTO {

    private Long promotionId;
    private String promotionTitle;
    private LocalDateTime promotionStartDate;
    private LocalDateTime promotionEndDate;
    private Long discountRate;
}
