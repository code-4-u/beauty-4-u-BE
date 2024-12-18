package com.beauty4u.backend.promotion.query.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FindPromotionGoodsSalesDTO {

    private Long promotionId;
    private String promotionTitle;
    private LocalDateTime promotionStartDate;
    private LocalDateTime promotionEndDate;
    private Long discountRate;
}
