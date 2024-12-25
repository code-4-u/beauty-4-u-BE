package com.beauty4u.backend.promotion.query.controller;

import com.beauty4u.backend.common.response.ApiResponse;
import com.beauty4u.backend.common.response.ResponseUtil;
import com.beauty4u.backend.common.success.SuccessCode;
import com.beauty4u.backend.promotion.query.dto.FindPromotionByCustomerGoodsResDTO;
import com.beauty4u.backend.promotion.query.dto.FindPromotionByCustomerListResDTO;
import com.beauty4u.backend.promotion.query.dto.FindPromotionResDTO;
import com.beauty4u.backend.promotion.query.service.PromotionNotiQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/promotionNoti")
@Tag(name = "promotionNoti", description = "프로모션알림 조회")
public class PromotionNotiQueryController {

    private final PromotionNotiQueryService promotionNotiQueryService;

    @Operation(summary = "프로모션 상세 조회", description = "프로모션을 상세 조회한다.")
    @GetMapping("/searchPromotion")
    public ResponseEntity<ApiResponse<List<FindPromotionResDTO>>> findPromotion(
            @RequestParam("promotionName") String promotionName) {

        List<FindPromotionResDTO> result = promotionNotiQueryService.findPromotion(promotionName);

        return ResponseUtil.successResponse(SuccessCode.PROMOTION_FIND_LIST_SUCCESS, result);
    }

    @Operation(summary = "프로모션 대상자 목록 조회", description = "프로모션 대상자 목록을 조회한다.")
    @GetMapping("/by-customer-goods")
    public ResponseEntity<ApiResponse<FindPromotionByCustomerListResDTO>> findPromotionByCustomerGoods(
            @RequestParam("promotionId") Integer promotionId,
            @RequestParam("page") Integer page,
            @RequestParam("count") Integer count) {

        List<FindPromotionByCustomerGoodsResDTO> customerList =
                promotionNotiQueryService.findPromotionByCustomerGoods(promotionId, page, count);

        Integer customerCount = promotionNotiQueryService.findPromotionByCustomerGoodsCount(promotionId);

        FindPromotionByCustomerListResDTO result = new FindPromotionByCustomerListResDTO();

        result.setFindPromotionByCustomerGoodsResList(customerList);
        result.setCustomerCount(customerCount);

        return ResponseUtil.successResponse(SuccessCode.PROMOTION_FIND_LIST_SUCCESS, result);
    }
}
