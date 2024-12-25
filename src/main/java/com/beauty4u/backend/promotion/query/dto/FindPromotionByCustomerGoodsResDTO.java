package com.beauty4u.backend.promotion.query.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FindPromotionByCustomerGoodsResDTO {
    private String customerCode;
    private String customerGender;
    private String customerAge;
    private String customerSkintype;
    private String customerGrade;
}
