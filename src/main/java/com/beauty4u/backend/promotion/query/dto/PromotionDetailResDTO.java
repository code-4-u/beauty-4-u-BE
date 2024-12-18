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
public class PromotionDetailResDTO {

    private Long promotionId;
    private Long promotionTypeId;
    private String promotionTypeName;
    private String promotionTitle;
    private LocalDateTime promotionStartDate;
    private LocalDateTime promotionEndDate;
    private String promotionStatus;
}
