package com.beauty4u.backend.inquiry.query.controller;

import com.beauty4u.backend.common.response.ApiResponse;
import com.beauty4u.backend.common.response.ResponseUtil;
import com.beauty4u.backend.common.success.SuccessCode;
import com.beauty4u.backend.inquiry.query.dto.InquiryFilterReqDTO;
import com.beauty4u.backend.inquiry.query.dto.InquiryListResDTO;
import com.beauty4u.backend.inquiry.query.service.InquiryQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/inquiry")
@Tag(name = "Inquiry", description = "문의 관련 API")
public class InquiryQueryController {

    private final InquiryQueryService inquiryQueryService;

    @Operation(summary = "QnA 목록 조회", description = "Q&A 목록을 조회한다.")
    @GetMapping("/list")
    public ResponseEntity<ApiResponse<InquiryListResDTO>> findInquiryList(
            InquiryFilterReqDTO inquiryFilterReqDTO) {

        InquiryListResDTO inquiryListResDTOS = inquiryQueryService.findInquiryList(inquiryFilterReqDTO);

        return ResponseUtil.successResponse(SuccessCode.INQUIRY_FIND_LIST_SUCCESS, inquiryListResDTOS);
    }
}
