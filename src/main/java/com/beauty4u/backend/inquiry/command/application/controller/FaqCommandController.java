package com.beauty4u.backend.inquiry.command.application.controller;

import com.beauty4u.backend.common.response.ApiResponse;
import com.beauty4u.backend.common.response.ResponseUtil;
import com.beauty4u.backend.common.success.SuccessCode;
import com.beauty4u.backend.common.util.CustomUserUtil;
import com.beauty4u.backend.inquiry.command.application.dto.FaqReqDTO;
import com.beauty4u.backend.inquiry.command.application.service.FaqService;
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
public class FaqCommandController {

    private final FaqService faqService;

    @Operation(summary = "FAQ 등록", description = "회원(관리자)이 FAQ를 등록한다.")
    @PostMapping("/faq")
    public ResponseEntity<ApiResponse<Void>> saveFaq(
            @RequestBody @Valid FaqReqDTO faqReqDTO) {

        String loginUserCode = CustomUserUtil.getCurrentUserCode();

        faqService.saveFaq(loginUserCode, faqReqDTO);

        return ResponseUtil.successResponse(SuccessCode.FAQ_SAVE_SUCCESS);
    }
}
