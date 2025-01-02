package com.beauty4u.backend.inquiry.query.controller;

import com.beauty4u.backend.common.response.ApiResponse;
import com.beauty4u.backend.common.response.ResponseUtil;
import com.beauty4u.backend.common.success.SuccessCode;
import com.beauty4u.backend.inquiry.query.dto.InquiryReplyResDTO;
import com.beauty4u.backend.inquiry.query.service.InquiryReplyQueryService;
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
@RequestMapping("/api/v1/inquiryReply/")
@Tag(name = "Inquiry", description = "문의 관련 API")
public class InquiryReplyQueryController {

    private final InquiryReplyQueryService inquiryReplyQueryService;

    @Operation(summary = "문의 답글 조회", description = "문의에 대한 답글을 조회한다.")
    @GetMapping("/{inquiryId}")
    public ResponseEntity<ApiResponse<InquiryReplyResDTO>> findInquiryReply(
            @PathVariable Long inquiryId
    ) {

        InquiryReplyResDTO inquiryDetail = inquiryReplyQueryService.findInquiryReplyDetail(inquiryId);

        return ResponseUtil.successResponse(SuccessCode.INQUIRY_REPLY_FIND_SUCCESS, inquiryDetail);
    }
}
