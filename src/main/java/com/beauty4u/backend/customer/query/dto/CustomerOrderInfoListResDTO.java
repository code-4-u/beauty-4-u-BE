package com.beauty4u.backend.customer.query.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CustomerOrderInfoListResDTO {

    private LocalDateTime orderCreatedDate;
    private Long orderId;
    private String goodsName;
    private Long orderCount;
    private Long orderPrice;
    private String orderStatus;
}
