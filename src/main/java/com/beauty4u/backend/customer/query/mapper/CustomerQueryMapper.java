package com.beauty4u.backend.customer.query.mapper;

import com.beauty4u.backend.customer.query.dto.CustomerListResDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CustomerQueryMapper {

    List<CustomerListResDTO> findCustomerList(
            @Param("customerCode") String customerCode,
            @Param("customerName") String customerName,
            @Param("customerGrade") String customerGrade,
            @Param("customerGender") String customerGender,
            @Param("startAge") Integer startAge,
            @Param("endAge") Integer endAge,
            @Param("sort") String sort,
            @Param("order") String order,
            @Param("offset") Long offset,
            @Param("count") Long count);
}
