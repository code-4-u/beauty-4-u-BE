package com.beauty4u.backend.customer.query.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDetailResDTO {

    private String customerCode;
    private String customerName;
    private String customerPhone;
    private Integer customerAge;
    private String customerGender;
    private String customerSkintype;
    private Long allOrderPrice; // 누적 총 구매 금액
    private Long cancelRefundCount; // 취소 환불 횟수
    private LocalDateTime customerLastOrderDate; // 최근 구매일
}
