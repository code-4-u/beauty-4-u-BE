package com.beauty4u.backend.customer.query.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderHistoryFilterDTO {

    private LocalDate startDate;
    private LocalDate endDate;
    private Long orderId;
    private String goodsName;
    private Long minPrice;
    private Long maxPrice;
    private String orderStatus;
    private String sort = "orderCreatedDate";
    private String order = "desc";
    private Long page = 1L;
    private Long count = 10L;
}
