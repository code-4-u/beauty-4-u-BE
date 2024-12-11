package com.beauty4u.backend.inquiry.query.controller;

import com.beauty4u.backend.common.response.ApiResponse;
import com.beauty4u.backend.common.response.ResponseUtil;
import com.beauty4u.backend.common.success.SuccessCode;
import com.beauty4u.backend.inquiry.query.dto.FaqDetailResDTO;
import com.beauty4u.backend.inquiry.query.dto.FaqListResDTO;
import com.beauty4u.backend.inquiry.query.service.FaqQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/inquiry")
@Tag(name = "Inquiry", description = "문의 관련 API")
public class FaqQueryController {

    private final FaqQueryService faqQueryService;

    @Operation(summary = "FAQ 목록 조회", description = "FAQ 목록을 조회한다.")
    @GetMapping("/faq/list")
    public ResponseEntity<ApiResponse<List<FaqListResDTO>>> findFaqList(
            @RequestParam(defaultValue = "1") Long page,
            @RequestParam(defaultValue = "10") Long count) {

        List<FaqListResDTO> faqListResDTOS = faqQueryService.findFaqList(page, count);

        return ResponseUtil.successResponse(SuccessCode.FAQ_FIND_LIST_SUCCESS, faqListResDTOS);
    }

    @Operation(summary = "FAQ 상세 조회", description = "등록된 FAQ를 상세 조회한다.")
    @GetMapping("/faq/{faqId}")
    public ResponseEntity<ApiResponse<FaqDetailResDTO>> findFaqDetail(
            @PathVariable Long faqId) {

        FaqDetailResDTO faqDetailResDTO = faqQueryService.findFaqDetail(faqId);

        return ResponseUtil.successResponse(SuccessCode.FAQ_FIND_DETAIL_SUCCESS, faqDetailResDTO);
    }
}
