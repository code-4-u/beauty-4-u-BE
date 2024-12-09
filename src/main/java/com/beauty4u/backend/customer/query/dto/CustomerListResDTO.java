package com.beauty4u.backend.customer.query.dto;

import com.beauty4u.backend.customer.command.domain.aggregate.CustomerGrade;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerListResDTO {

    private String customerCode;
    private String customerName;
    private String customerPhone;
    private CustomerGrade customerGrade;
    private String customerGender;
    private Long customerAge;
    private Long totalPurchaseAmount; // 누적 총 구매 금액
    private Long totalPurchaseCount;
    private LocalDateTime customerLastOrderDate;
    private LocalDateTime createdDate;
}
