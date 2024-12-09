package com.beauty4u.backend.customer.query.controller;

import com.beauty4u.backend.common.response.ApiResponse;
import com.beauty4u.backend.common.response.ResponseUtil;
import com.beauty4u.backend.common.success.SuccessCode;
import com.beauty4u.backend.customer.query.dto.*;
import com.beauty4u.backend.customer.query.service.CustomerQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/customer")
@Tag(name = "Customer", description = "고객")
public class CustomerQueryController {

    private final CustomerQueryService customerQueryService;

    @Operation(summary = "고객 목록 조회", description = "고객 목록을 조회한다.")
    @GetMapping("/list")
    public ResponseEntity<ApiResponse<CustomerListResDTO>> findCustomerList(CustomerFilterRequest customerFilterRequest) {

        CustomerListResDTO customerListResDTOS
                = customerQueryService.findCustomerList(customerFilterRequest);

        return ResponseUtil.successResponse(SuccessCode.CUSTOMER_FIND_LIST_SUCCESS, customerListResDTOS);
    }

    @Operation(summary = "고객 상세 조회", description = "고객의 정보를 상세 조회한다.")
    @GetMapping("/{customerCode}")
    public ResponseEntity<ApiResponse<CustomerDetailResDTO>> findCustomerDetail(@PathVariable String customerCode) {

        CustomerDetailResDTO customerDetailResDTO
                = customerQueryService.findCustomerDetail(customerCode);

        return ResponseUtil.successResponse(SuccessCode.CUSTOMER_FIND_DETAIL_SUCCESS, customerDetailResDTO);
    }

    @Operation(summary = "고객 주문 이력 조회", description = "고객의 주문 이력 목록을 조회한다.")
    @GetMapping("/{customerCode}/orderinfo/list")
    public ResponseEntity<ApiResponse<CustomerOrderInfoListResDTO>> findCustomerOrderInfoList(
            @PathVariable String customerCode,
            OrderHistoryFilterDTO orderHistoryFilterDTO) {

        CustomerOrderInfoListResDTO customerOrderInfoListResDTO
                = customerQueryService.findCustomerOrderInfoList(customerCode,orderHistoryFilterDTO);

        return ResponseUtil.successResponse(SuccessCode.CUSTOMER_FIND_ORDERINFO_LIST_SUCCESS, customerOrderInfoListResDTO);
    }

    @Operation(summary = "고객 목록 통계 요약 조회", description = "고객 목록 통계 요약을 조회한다.")
    @GetMapping("/list/stats")
    public ResponseEntity<ApiResponse<CustomerListStatsDTO>> findCustomerListStats() {

        CustomerListStatsDTO customerListStatsDTO = customerQueryService.findCustomerListStats();

        return ResponseUtil.successResponse(SuccessCode.CUSTOMER_FIND_LIST_STATS_SUCCESS, customerListStatsDTO);
    }
}
