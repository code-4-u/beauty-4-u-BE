package com.beauty4u.backend.promotion.query.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FindPromotionReqDTO {
    private String searchKeyword;
    private String startDate;
    private String endDate;
    private Integer promotionTypeId;
    private String promotionStatus;
}
