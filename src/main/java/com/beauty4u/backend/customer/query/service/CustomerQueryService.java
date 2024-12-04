package com.beauty4u.backend.customer.query.service;

import com.beauty4u.backend.customer.query.dto.CustomerListResDTO;
import com.beauty4u.backend.customer.query.mapper.CustomerQueryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerQueryService {

    private final CustomerQueryMapper customerQueryMapper;

    public List<CustomerListResDTO> findCustomerList(Long page, Long count) {

        long offset = (page - 1) * count;

        return customerQueryMapper.findCustomerList(offset, count);
    }
}
