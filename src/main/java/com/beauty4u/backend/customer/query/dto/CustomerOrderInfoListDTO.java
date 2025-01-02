package com.beauty4u.backend.customer.query.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerOrderInfoListDTO {

    private LocalDateTime createdDate;
    private Long orderId;
    private String goodsName;
    private Long orderCount;
    private Long orderPrice;
    private String orderStatus;
}
