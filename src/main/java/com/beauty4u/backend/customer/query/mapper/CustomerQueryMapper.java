package com.beauty4u.backend.customer.query.mapper;

import com.beauty4u.backend.customer.query.dto.CustomerListResDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CustomerQueryMapper {

    List<CustomerListResDTO> findCustomerList(
            @Param("offset") Long offset,
            @Param("count") Long count);
}
