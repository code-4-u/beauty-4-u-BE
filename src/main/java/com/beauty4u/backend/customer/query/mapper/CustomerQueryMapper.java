package com.beauty4u.backend.customer.query.mapper;

import com.beauty4u.backend.customer.query.dto.CustomerDetailResDTO;
import com.beauty4u.backend.customer.query.dto.CustomerListResDTO;
import com.beauty4u.backend.customer.query.dto.CustomerListStatsDTO;
import com.beauty4u.backend.customer.query.dto.CustomerOrderInfoListResDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
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

    CustomerDetailResDTO findCustomerDetail(@Param("customerCode") String customerCode);

    List<CustomerOrderInfoListResDTO> findCustomerOrderInfoList(
            @Param("customerCode") String customerCode,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate,
            @Param("orderId") Long orderId,
            @Param("goodsName") String goodsName,
            @Param("minPrice") Long minPrice,
            @Param("maxPrice") Long maxPrice,
            @Param("orderStatus") String orderStatus,
            @Param("sort") String sort,
            @Param("order") String order,
            @Param("offset") Long offset,
            @Param("count") Long count
            );

    CustomerListStatsDTO findCustomerListStats(
            @Param("now") LocalDateTime now,
            @Param("oneMonthAgo") LocalDateTime oneMonthAgo
    );
}
