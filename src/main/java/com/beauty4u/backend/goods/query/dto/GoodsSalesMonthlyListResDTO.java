package com.beauty4u.backend.goods.query.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GoodsSalesMonthlyListResDTO {

    private Integer month;
    private Long sales;
}
