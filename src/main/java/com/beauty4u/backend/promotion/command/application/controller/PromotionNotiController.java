package com.beauty4u.backend.promotion.command.application.controller;

import com.beauty4u.backend.common.response.ApiResponse;
import com.beauty4u.backend.common.response.ResponseUtil;
import com.beauty4u.backend.common.success.SuccessCode;
import com.beauty4u.backend.promotion.command.application.dto.PromotionEmailResult;
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

    @Operation(summary = "프로모션 알림 메일 전송", description = "프로모션 알림을 메일로 전송한다. FakeSMTP 사용")
    @PostMapping("/notiFake/{promotionId}")
    public ResponseEntity<ApiResponse<PromotionEmailResult>> savePromotionNoti(@PathVariable Long promotionId) {

        PromotionEmailResult result = promotionNotiService.savePromotionNotiFakeSMTP(promotionId);

        return ResponseUtil.successResponse(SuccessCode.PROMOTION_NOTI_SAVE_SUCCESS, result);
    }

    @Operation(summary = "프로모션 알림 메일 실제 SMTP 서버 전송", description = "프로모션 알림을 실제 메일 서버로 전송한다.")
    @PostMapping("/noti/{promotionId}")
    public ResponseEntity<ApiResponse<PromotionEmailResult>> savePromotionNotiReal(@PathVariable Long promotionId) {

        PromotionEmailResult result = promotionNotiService.sendPromotionNotiGmail(promotionId);

        return ResponseUtil.successResponse(SuccessCode.PROMOTION_NOTI_SAVE_SUCCESS, result);
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
