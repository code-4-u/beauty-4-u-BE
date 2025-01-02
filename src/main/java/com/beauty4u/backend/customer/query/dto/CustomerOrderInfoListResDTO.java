package com.beauty4u.backend.customer.query.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CustomerOrderInfoListResDTO {

    private List<CustomerOrderInfoListDTO> customerOrderInfoList;
    private Long totalCount;
}
