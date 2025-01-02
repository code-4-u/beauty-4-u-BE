package com.beauty4u.backend.goods.query.controller;

import com.beauty4u.backend.common.response.ApiResponse;
import com.beauty4u.backend.common.response.ResponseUtil;
import com.beauty4u.backend.common.success.SuccessCode;
import com.beauty4u.backend.goods.query.dto.GeminiReviewResDTO;
import com.beauty4u.backend.goods.query.service.GeminiReviewQueryService;
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
@RequestMapping("/api/v1/review")
@Tag(name = "Review", description = "리뷰 요약")
public class ReviewAiQueryController {

    private final GeminiReviewQueryService geminiReviewQueryService;

    @Operation(summary = "제미나이 리뷰 요약 요청", description = "제미나이 api를 사용해서 리뷰 요약을 받는다.")
    @GetMapping("/{goodsCode}")
    public ResponseEntity<ApiResponse<GeminiReviewResDTO>> getGeminiReview(
            @PathVariable String goodsCode
    ) {

        GeminiReviewResDTO result = geminiReviewQueryService.getGeminiReviewSummary(goodsCode);

        return ResponseUtil.successResponse(SuccessCode.GEMINI_REVIEW_FIND_SUCCESS, result);
    }
}
