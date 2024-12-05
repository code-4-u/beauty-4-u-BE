package com.beauty4u.backend.customer.query.service;

import com.beauty4u.backend.customer.query.dto.CustomerDetailResDTO;
import com.beauty4u.backend.customer.query.dto.CustomerFilterRequest;
import com.beauty4u.backend.customer.query.dto.CustomerListResDTO;
import com.beauty4u.backend.customer.query.mapper.CustomerQueryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerQueryService {

    private final CustomerQueryMapper customerQueryMapper;

    public List<CustomerListResDTO> findCustomerList(CustomerFilterRequest customerFilterRequest) {

        Long offset = (customerFilterRequest.getPage() - 1) * customerFilterRequest.getCount();
        Integer startAge = null;
        Integer endAge = null;

        if (customerFilterRequest.getAgeGroup() != null) {
             startAge = customerFilterRequest.getAgeGroup();
             endAge = customerFilterRequest.getAgeGroup() + 9;
            if (startAge == 60) {
                endAge = 100;
            }
        }

        return customerQueryMapper.findCustomerList(
                customerFilterRequest.getCode(),
                customerFilterRequest.getName(),
                customerFilterRequest.getGrade(),
                customerFilterRequest.getGender(),
                startAge,
                endAge,
                customerFilterRequest.getSort(),
                customerFilterRequest.getOrder(),
                offset,
                customerFilterRequest.getCount());
    }

    public CustomerDetailResDTO findCustomerDetail(String customerCode) {

        return customerQueryMapper.findCustomerDetail(customerCode);
    }
}
