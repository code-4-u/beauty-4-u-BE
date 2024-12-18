package com.beauty4u.backend.promotion.command.application.controller;

import com.beauty4u.backend.common.response.ApiResponse;
import com.beauty4u.backend.common.response.ResponseUtil;
import com.beauty4u.backend.common.success.SuccessCode;
import com.beauty4u.backend.promotion.command.application.dto.DeletePromotionGoodsReqDTO;
import com.beauty4u.backend.promotion.command.application.dto.SavePromotionGoodsReqDTO;
import com.beauty4u.backend.promotion.command.application.dto.UpdatePromotionGoodsReqDTO;
import com.beauty4u.backend.promotion.command.application.service.PromotionGoodsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @Operation(summary = "프로모션 적용 상품 목록 삭제", description = "프로모션에 적용된 상품을 목록으로 삭제한다.")
    @DeleteMapping
    public ResponseEntity<ApiResponse<Void>> updatePromotionGoods(
            @RequestBody DeletePromotionGoodsReqDTO deletePromotionGoodsReqDTO
    ) {

        promotionGoodsService.deletePromotionGoodsList(deletePromotionGoodsReqDTO);

        return ResponseUtil.successResponse(SuccessCode.PROMOTION_GOODS_LIST_DELETE_SUCCESS);
    }

    @Operation(summary = "프로모션 적용 상품 할인율 수정", description = "프로모션에 적용된 상품에 대한 할인율을 수정한다.")
    @PutMapping
    public ResponseEntity<ApiResponse<Void>> updatePromotionGoods(
            @RequestBody UpdatePromotionGoodsReqDTO updatePromotionGoodsReqDTO
    ) {

        promotionGoodsService.updatePromotionGoods(updatePromotionGoodsReqDTO);

        return ResponseUtil.successResponse(SuccessCode.PROMOTION_GOODS_LIST_UPDATE_SUCCESS);
    }
}
