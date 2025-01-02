package com.beauty4u.backend.customer.query.dto;

import com.beauty4u.backend.customer.command.domain.aggregate.CustomerGrade;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerListResDTO {

    private Long totalCount;
    private List<CustomerListDTO> customerList;
}
