package com.beauty4u.backend.goods.query.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GoodsFilterReqDTO {

    private String topCategoryCode;
    private String subCategoryCode;
    private Long minPrice;
    private Long maxPrice;
    private String sort;
    private String order;
    private Long page;
    private Long count;
}
