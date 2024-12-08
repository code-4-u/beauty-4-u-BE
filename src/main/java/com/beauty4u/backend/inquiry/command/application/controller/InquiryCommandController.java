package com.beauty4u.backend.inquiry.command.application.controller;

import com.beauty4u.backend.common.response.ApiResponse;
import com.beauty4u.backend.common.response.ResponseUtil;
import com.beauty4u.backend.common.success.SuccessCode;
import com.beauty4u.backend.common.util.CustomUserUtil;
import com.beauty4u.backend.inquiry.command.application.dto.QnaReqDTO;
import com.beauty4u.backend.inquiry.command.application.service.InquiryCommandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/inquiry")
@Tag(name = "Inquiry", description = "문의 관련 API")
public class InquiryCommandController {

    private final InquiryCommandService inquiryCommandService;

    @Operation(summary = "QnA 질문(Q) 등록", description = "회원(직원)이 질문을 등록한다.")
    @PostMapping
    public ResponseEntity<ApiResponse<Void>> saveQna(
            @RequestBody @Valid QnaReqDTO qnaReqDTO) {

        String loginUserCode = CustomUserUtil.getCurrentUserCode();

        inquiryCommandService.saveQna(loginUserCode, qnaReqDTO);

        return ResponseUtil.successResponse(SuccessCode.INQUIRY_SAVE_SUCCESS);
    }

    @Operation(summary = "QnA 질문(Q) 수정", description = "회원(직원)이 질문을 수정한다.")
    @PutMapping("/{inquiryId}")
    public ResponseEntity<ApiResponse<Void>> updateQna(
            @PathVariable Long inquiryId,
            @RequestBody @Valid QnaReqDTO qnaReqDTO) {

        inquiryCommandService.updateQna(inquiryId, qnaReqDTO);

        return ResponseUtil.successResponse(SuccessCode.INQUIRY_UPDATE_SUCCESS);
    }
}
