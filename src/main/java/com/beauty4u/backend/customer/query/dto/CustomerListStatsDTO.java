package com.beauty4u.backend.customer.query.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerListStatsDTO {

    private Long customerCount; // 전체 고객 수
    private Long customerGoldCount; // Gold 고객 수
    private Long customerNewCount; // 신규 고객 수
    private Long customerDormantCount; // 휴면 고객 수
}
