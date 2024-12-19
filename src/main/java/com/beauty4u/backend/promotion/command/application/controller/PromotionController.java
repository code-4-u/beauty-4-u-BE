package com.beauty4u.backend.promotion.command.application.controller;

import com.beauty4u.backend.common.response.ApiResponse;
import com.beauty4u.backend.common.response.ResponseUtil;
import com.beauty4u.backend.common.success.SuccessCode;
import com.beauty4u.backend.promotion.command.application.dto.SavePromotionReqDTO;
import com.beauty4u.backend.promotion.command.application.service.PromotionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/promotion")
@Tag(name = "Promotion", description = "프로모션")
public class PromotionController {

    private final PromotionService promotionService;

    @Operation(summary = "프로모션 등록", description = "프로모션을 등록한다.")
    @PostMapping
    public ResponseEntity<ApiResponse<Long>> savePromotion(
            @RequestBody SavePromotionReqDTO savePromotionReqDTO
    ) {

        Long promotionId = promotionService.savePromotion(savePromotionReqDTO);

        return ResponseUtil.successResponse(SuccessCode.PROMOTION_SAVE_SUCCESS, promotionId);
    }

    @Operation(summary = "프로모션 수정", description = "프로모션을 수정한다.")
    @PutMapping("/{promotionId}")
    public ResponseEntity<ApiResponse<Void>> updatePromotion(
            @PathVariable Long promotionId,
            @RequestBody SavePromotionReqDTO savePromotionReqDTO
    ) {

        promotionService.updatePromotion(promotionId, savePromotionReqDTO);

        return ResponseUtil.successResponse(SuccessCode.PROMOTION_UPDATE_SUCCESS);
    }

    @Operation(summary = "프로모션 삭제", description = "프로모션을 삭제한다.")
    @DeleteMapping("/{promotionId}")
    public ResponseEntity<ApiResponse<Void>> deletePromotion(
            @PathVariable Long promotionId
    ) {

        promotionService.deletePromotion(promotionId);

        return ResponseUtil.successResponse(SuccessCode.PROMOTION_DELETE_SUCCESS);
    }
}
