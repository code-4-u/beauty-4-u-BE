package com.beauty4u.backend.inquiry.command.application.controller;

import com.beauty4u.backend.common.response.ApiResponse;
import com.beauty4u.backend.common.response.ResponseUtil;
import com.beauty4u.backend.common.success.SuccessCode;
import com.beauty4u.backend.common.util.CustomUserUtil;
import com.beauty4u.backend.inquiry.command.application.dto.CreateQnaReqDTO;
import com.beauty4u.backend.inquiry.command.application.service.InquiryCommandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/inquiry")
@Tag(name = "Inquiry", description = "문의 관련 API")
public class InquiryCommandController {

    private final InquiryCommandService inquiryCommandService;

    @Operation(summary = "QnA 질문(Q) 등록", description = "회원(직원)이 질문을 등록한다.")
    @PostMapping
    public ResponseEntity<ApiResponse<Void>> saveQna(
            @RequestBody @Valid CreateQnaReqDTO createQnaReqDTO) {

        String loginUserCode = CustomUserUtil.getCurrentUserCode();

        inquiryCommandService.saveQna(loginUserCode, createQnaReqDTO);

        return ResponseUtil.successResponse(SuccessCode.INQUIRY_SAVE_SUCCESS);
    }
}
