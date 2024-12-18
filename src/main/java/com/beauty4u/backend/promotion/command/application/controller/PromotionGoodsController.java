package com.beauty4u.backend.promotion.command.application.controller;

import com.beauty4u.backend.common.response.ApiResponse;
import com.beauty4u.backend.common.response.ResponseUtil;
import com.beauty4u.backend.common.success.SuccessCode;
import com.beauty4u.backend.promotion.command.application.dto.SavePromotionGoodsReqDTO;
import com.beauty4u.backend.promotion.command.application.service.PromotionGoodsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/promotionGoods")
@Tag(name = "PromotionGoods", description = "프로모션 적용 상품")
public class PromotionGoodsController {

    private final PromotionGoodsService promotionGoodsService;

    @Operation(summary = "프로모션 적용 상품 등록", description = "프로모션에 상품을 등록한다.")
    @PostMapping
    public ResponseEntity<ApiResponse<Void>> savePromotionGoodsList(
            @RequestBody SavePromotionGoodsReqDTO savePromotionGoodsReqDTO
    ) {

        promotionGoodsService.savePromotionGoodsList(savePromotionGoodsReqDTO);

        return ResponseUtil.successResponse(SuccessCode.PROMOTION_GOODS_LIST_SAVE_SUCCESS);
    }
}
