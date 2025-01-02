package com.beauty4u.backend.promotion.query.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FindPromotionNotiTargetResDTO {
    private String customerCode;
    private String customerName;
    private String customerEmail;
    private String promotionTitle;
    private Integer discountRate;
    private String goodsName;
}
