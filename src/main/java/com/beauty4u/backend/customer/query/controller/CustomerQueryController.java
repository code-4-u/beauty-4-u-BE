package com.beauty4u.backend.customer.query.controller;

import com.beauty4u.backend.common.response.ApiResponse;
import com.beauty4u.backend.common.response.ResponseUtil;
import com.beauty4u.backend.common.success.SuccessCode;
import com.beauty4u.backend.customer.query.dto.CustomerFilterRequest;
import com.beauty4u.backend.customer.query.dto.CustomerListResDTO;
import com.beauty4u.backend.customer.query.service.CustomerQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/customer")
@Tag(name = "Customer", description = "고객")
public class CustomerQueryController {

    private final CustomerQueryService customerQueryService;

    @Operation(summary = "고객 목록 조회", description = "고객 목록을 조회한다.")
    @GetMapping("/list")
    public ResponseEntity<ApiResponse<List<CustomerListResDTO>>> findCustomerList(CustomerFilterRequest customerFilterRequest) {

        List<CustomerListResDTO> customerListResDTOS = customerQueryService.findCustomerList(customerFilterRequest);

        return ResponseUtil.successResponse(SuccessCode.CUSTOMER_FIND_LIST_SUCCESS, customerListResDTOS);
    }

//    @Operation(summary = "고객 상세 조회", description = "고객의 정보를 상세 조회한다.")
//    @GetMapping("/customer/{customerCode}")
//    public
}
