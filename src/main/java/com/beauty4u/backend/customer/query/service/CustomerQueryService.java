package com.beauty4u.backend.customer.query.service;

import com.beauty4u.backend.customer.query.dto.*;
import com.beauty4u.backend.customer.query.mapper.CustomerQueryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerQueryService {

    private final CustomerQueryMapper customerQueryMapper;

    public CustomerListResDTO findCustomerList(CustomerFilterRequest customerFilterRequest) {

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

        List<CustomerListDTO> customerList = customerQueryMapper.findCustomerList(
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

        CustomerListResDTO customerListResDTO = new CustomerListResDTO();
        customerListResDTO.setCustomerList(customerList);

        Long totalCount = customerQueryMapper.findCustomerListTotalCount(
                customerFilterRequest.getCode(),
                customerFilterRequest.getName(),
                customerFilterRequest.getGrade(),
                customerFilterRequest.getGender(),
                startAge,
                endAge,
                customerFilterRequest.getSort(),
                customerFilterRequest.getOrder()
        );

        customerListResDTO.setTotalCount(totalCount);

        return customerListResDTO;
    }

    public CustomerDetailResDTO findCustomerDetail(String customerCode) {

        return customerQueryMapper.findCustomerDetail(customerCode);
    }

    public CustomerOrderInfoListResDTO findCustomerOrderInfoList(String customerCode, OrderHistoryFilterDTO orderHistoryFilterDTO) {

        Long offset = (orderHistoryFilterDTO.getPage() - 1) * orderHistoryFilterDTO.getCount();

        LocalDateTime startDateTime = orderHistoryFilterDTO.getStartDate().atStartOfDay(); // 시작일의 00:00:00
        LocalDateTime endDateTime = orderHistoryFilterDTO.getEndDate().atTime(23, 59, 59); // 종료일의 23:59:59

        List<CustomerOrderInfoListDTO> customerOrderInfoList = customerQueryMapper.findCustomerOrderInfoList(
                customerCode,
                startDateTime,
                endDateTime,
                orderHistoryFilterDTO.getOrderId(),
                orderHistoryFilterDTO.getGoodsName(),
                orderHistoryFilterDTO.getMinPrice(),
                orderHistoryFilterDTO.getMaxPrice(),
                orderHistoryFilterDTO.getOrderStatus(),
                orderHistoryFilterDTO.getSort(),
                orderHistoryFilterDTO.getOrder(),
                offset,
                orderHistoryFilterDTO.getCount()
        );

        CustomerOrderInfoListResDTO customerOrderInfoListResDTO = new CustomerOrderInfoListResDTO();
        customerOrderInfoListResDTO.setCustomerOrderInfoList(customerOrderInfoList);

        Long totalCount = customerQueryMapper.findCustomerOrderInfoTotalCount(
                customerCode,
                startDateTime,
                endDateTime,
                orderHistoryFilterDTO.getOrderId(),
                orderHistoryFilterDTO.getGoodsName(),
                orderHistoryFilterDTO.getMinPrice(),
                orderHistoryFilterDTO.getMaxPrice(),
                orderHistoryFilterDTO.getOrderStatus(),
                orderHistoryFilterDTO.getSort(),
                orderHistoryFilterDTO.getOrder()
        );

        customerOrderInfoListResDTO.setTotalCount(totalCount);

        return customerOrderInfoListResDTO;
    }

    public CustomerListStatsDTO findCustomerListStats() {

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime oneMonthAgo = now.minusMonths(1);

        return customerQueryMapper.findCustomerListStats(now, oneMonthAgo);
    }
}
