package com.beauty4u.backend.promotion.query.controller;

import com.beauty4u.backend.common.response.ApiResponse;
import com.beauty4u.backend.common.response.ResponseUtil;
import com.beauty4u.backend.common.success.SuccessCode;
import com.beauty4u.backend.promotion.query.dto.FindPromotionListReqDTO;
import com.beauty4u.backend.promotion.query.dto.PromotionDetailResDTO;
import com.beauty4u.backend.promotion.query.service.PromotionQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/promotion")
@Tag(name = "Promotion", description = "프로모션")
public class PromotionQueryController {

    private final PromotionQueryService promotionQueryService;

    @Operation(summary = "프로모션 상세 조회", description = "프로모션을 상세 조회한다.")
    @GetMapping("/{promotionId}")
    public ResponseEntity<ApiResponse<PromotionDetailResDTO>> findPromotionDetail(@PathVariable Long promotionId) {

        PromotionDetailResDTO promotionDetailResDTO = promotionQueryService.findPromotionDetail(promotionId);

        return ResponseUtil.successResponse(SuccessCode.PROMOTION_FIND_DETAIL_SUCCESS, promotionDetailResDTO);
    }

    @Operation(summary = "프로모션 목록 조회", description = "프로모션 목록을 조회한다.")
    @GetMapping
    public ResponseEntity<ApiResponse<List<PromotionDetailResDTO>>> findPromotionList(
            FindPromotionListReqDTO findPromotionListReqDTO
    ) {

        List<PromotionDetailResDTO> promotionDetailResDTOS = promotionQueryService.findPromotionList(findPromotionListReqDTO);

        return ResponseUtil.successResponse(SuccessCode.PROMOTION_FIND_LIST_SUCCESS, promotionDetailResDTOS);
    }
}
