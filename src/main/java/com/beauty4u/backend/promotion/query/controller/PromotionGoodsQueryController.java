package com.beauty4u.backend.promotion.query.controller;

import com.beauty4u.backend.common.response.ApiResponse;
import com.beauty4u.backend.common.response.ResponseUtil;
import com.beauty4u.backend.common.success.SuccessCode;
import com.beauty4u.backend.promotion.query.dto.FindPromotionGoodsListFilterDTO;
import com.beauty4u.backend.promotion.query.dto.FindPromotionGoodsListResDTO;
import com.beauty4u.backend.promotion.query.service.PromotionGoodsQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/promotionGoods")
@Tag(name = "PromotionGoods", description = "프로모션 적용 상품")
public class PromotionGoodsQueryController {

    private final PromotionGoodsQueryService promotionGoodsQueryService;

    @Operation(summary = "프로모션 상품 목록 조회", description = "프로모션 상품 목록을 조회한다.")
    @GetMapping("{promotionId}")
    public ResponseEntity<ApiResponse<List<FindPromotionGoodsListResDTO>>> findPromotionGoodsList(
            @PathVariable("promotionId") Long promotionId,
            FindPromotionGoodsListFilterDTO findPromotionGoodsListFilterDTO
    ) {

        List<FindPromotionGoodsListResDTO> findPromotionGoodsListResDTOS
                =  promotionGoodsQueryService.findPromotionGoodsList(promotionId, findPromotionGoodsListFilterDTO);

        return ResponseUtil.successResponse(SuccessCode.PROMOTION_GOODS_LIST_FIND_SUCCESS, findPromotionGoodsListResDTOS);
    }
}
