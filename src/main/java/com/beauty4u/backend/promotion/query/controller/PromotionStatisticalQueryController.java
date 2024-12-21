package com.beauty4u.backend.promotion.query.controller;

import com.beauty4u.backend.common.response.ApiResponse;
import com.beauty4u.backend.common.response.ResponseUtil;
import com.beauty4u.backend.common.success.SuccessCode;
import com.beauty4u.backend.promotion.query.dto.*;
import com.beauty4u.backend.promotion.query.service.PromotionStatisticalQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/* 프로모션 통계 컨트로롤러 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/promotion-statistical")
@Tag(name="promotionStatistical", description = "프로모션 별 비교 관련 컨트롤러")
public class PromotionStatisticalQueryController {

    private final PromotionStatisticalQueryService promotionStatisticalQueryService;

    @Operation(summary = "프로모션 종류 조회")
    @GetMapping("/type")
    public ResponseEntity<ApiResponse<List<FindPromotionTypeResDTO>>> findPromotionType() {

        List<FindPromotionTypeResDTO> result = promotionStatisticalQueryService.findPromotionType();

        return ResponseUtil.successResponse(SuccessCode.PROMO_STAT_FIND_TYPE, result);
    }

    @Operation(summary = "프로모션 검색 기능")
    @GetMapping("/search-promotion")
    public ResponseEntity<ApiResponse<List<FindPromotionResDTO>>> findPromotion(
            @ModelAttribute FindPromotionReqDTO findpromotionReqDTO) {

        List<FindPromotionResDTO> result = promotionStatisticalQueryService.findPromotion(findpromotionReqDTO);

        return ResponseUtil.successResponse(SuccessCode.PROMO_STAT_SEARCH_SUCCESS, result);
    }

    @Operation(summary = "프로모션 타입별 조회 ASC 조회")
    @GetMapping("/type-by-promotion")
    public ResponseEntity<ApiResponse<List<FindPromotionByTypeResDTO>>> findTypeByPromotion() {

        List<FindPromotionByTypeResDTO> result =
                promotionStatisticalQueryService.findTypeByPromotion();

        return ResponseUtil.successResponse(SuccessCode.PROMO_STAT_FIND_TYPE_BY_PROMOTION, result);
    }

    @Operation(summary = "프로모션 종류에 따른 년도별 그래프 조회")
    @GetMapping("/by-year-sales")
    public ResponseEntity<ApiResponse<List<FindPromotionByYearSalesResDTO>>> findPromotionByYearSales(
            @RequestParam List<Integer> promoId) {

        List<FindPromotionByYearSalesResDTO> result =
                promotionStatisticalQueryService.findPromotionByYearSales(promoId);

        return ResponseUtil.successResponse(SuccessCode.PROMO_STAT_FIND_BY_YEAR_SALES, result);
    }

    @Operation(summary = "프로모션 기간 상품 매출 랭킹 비교")
    @GetMapping("/by-comparison-sales")
    public ResponseEntity<ApiResponse<List<FindPromotionByComparisonSalesResDTO>>> findPromotionByComparisonSales(
            @RequestParam Integer previousPromotionId, @RequestParam Integer afterPromotionId) {

        List<FindPromotionByComparisonSalesResDTO> result =
                promotionStatisticalQueryService.findPromotionByComparisonSales(previousPromotionId, afterPromotionId);

        return ResponseUtil.successResponse(SuccessCode.PROMO_STAT_FIND_BY_COM_SALES, result);
    }
}
