package com.beauty4u.backend.promotion.query.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PromotionDetailResDTO {

    private Long promotionId;
    private String promotionTitle;
    private LocalDateTime promotionStartTime;
    private LocalDateTime promotionEndTime;
    private String promotionStatus;
}
