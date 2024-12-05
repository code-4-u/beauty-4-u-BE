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
public class CustomerListResDTO {

    private String customerCode;
    private String customerName;
    private CustomerGrade customerGrade;
    private String customerGender;
    private Long customerAge;
    private LocalDateTime customerLastOrderDate;
    private LocalDateTime customerCreatedDate;
}
