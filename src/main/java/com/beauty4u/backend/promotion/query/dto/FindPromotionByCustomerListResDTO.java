package com.beauty4u.backend.promotion.query.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FindPromotionByCustomerListResDTO {
    private Integer customerCount;
    private List<FindPromotionByCustomerGoodsResDTO> findPromotionByCustomerGoodsResList;
}
