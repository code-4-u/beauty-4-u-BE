package com.beauty4u.backend.goods.query.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GoodsSalesResDTO {

    private Long currentYearMonthlySales; // 기준년도 월 매출액
    private Long lastYearMonthlySales;  // 기준년도 1년전 월 매출액
    private double percent; // 전년대비 기준년 매출액 증감율
}
