package com.beauty4u.backend.promotion.query.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FindPromotionResDTO {
    private Integer promotionId;
    private Integer promotionTypeId;
    private String promotionTitle;
    private String promotionContent;
    private LocalDateTime promotionStartDate;
    private LocalDateTime promotionEndDate;
    private String promotionStatus;
}
