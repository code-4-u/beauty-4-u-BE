package com.beauty4u.backend.promotion.query.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FindPromotionListReqDTO {

    private String promotionTitle;
    private LocalDate promotionStartDate;
    private LocalDate promotionEndDate;
    private String promotionStatus;
    private String sort;
    private String order;
    private Long page;
    private Long count;

}
