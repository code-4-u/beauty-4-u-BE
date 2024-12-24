package com.beauty4u.backend.promotion.command.application.controller;

import com.beauty4u.backend.common.response.ApiResponse;
import com.beauty4u.backend.common.response.ResponseUtil;
import com.beauty4u.backend.common.success.SuccessCode;
import com.beauty4u.backend.promotion.command.application.dto.SavePromotionNotiDTO;
import com.beauty4u.backend.promotion.command.application.dto.UpdatePromotionNotiDTO;
import com.beauty4u.backend.promotion.command.application.service.PromotionNotiService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/promotionNoti")
@Tag(name = "PromotionNoti", description = "프로모션 알림")
public class PromotionNotiController {

    private final PromotionNotiService promotionNotiService;

    @Operation(summary = "프로모션 알림 등록", description = "프로모션 알림을 등록을 한다.")
    @PostMapping("/")
    public ResponseEntity<ApiResponse<Void>> savePromotionNoti(@RequestBody SavePromotionNotiDTO savePromotionNotiDTO) {

        promotionNotiService.savePromotionNoti(savePromotionNotiDTO);

        return ResponseUtil.successResponse(SuccessCode.PROMOTION_NOTI_SAVE_SUCCESS);
    }

    @Operation(summary = "프로모션 알림 수정", description = "프로모션 알림을 수정 한다.")
    @PutMapping("/{promotionNotiId}")
    public ResponseEntity<ApiResponse<Void>> updatePromotionNoti(
            @PathVariable(value = "promotionNotiId") Long id,
            @RequestBody UpdatePromotionNotiDTO updatePromotionNotiDTO) {

        promotionNotiService.updatePromotionNoti(id, updatePromotionNotiDTO);

        return ResponseUtil.successResponse(SuccessCode.PROMOTION_NOTI_UPDATE_SUCCESS);
    }

    @Operation(summary = "프로모션 알림 삭제", description = "프로모션 알림을 삭제 한다.")
    @DeleteMapping("/{promotionNotiId}")
    public ResponseEntity<ApiResponse<Void>> deletePromotionNoti(
            @PathVariable(value = "promotionNotiId") Long id) {

        promotionNotiService.deletePromotionNoti(id);

        return ResponseUtil.successResponse(SuccessCode.PROMOTION_NOTI_DELETE_SUCCESS);
    }
}
