package com.beauty4u.backend.promotion.command.application.controller;

import com.beauty4u.backend.common.response.ApiResponse;
import com.beauty4u.backend.common.response.ResponseUtil;
import com.beauty4u.backend.common.success.SuccessCode;
import com.beauty4u.backend.promotion.command.application.dto.SavePromotionTypeDTO;
import com.beauty4u.backend.promotion.command.application.dto.UpdatePromotionTypeDTO;
import com.beauty4u.backend.promotion.command.application.service.PromotionTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/promotionType")
@Tag(name = "Promotion", description = "프로모션")
public class PromotionTypeController {

    private final PromotionTypeService promotionTypeService;

    @Operation(summary = "프로모션 등록", description = "프로모션 등록을 한다.")
    @PostMapping
    public ResponseEntity<ApiResponse<Void>> savePromotionType(
            @RequestBody SavePromotionTypeDTO savePromotionTypeDTO
    ) {

        promotionTypeService.savePromotionType(savePromotionTypeDTO);

        return ResponseUtil.successResponse(SuccessCode.PROMOTIONTYPE_SAVE_SUCCESS);
    }

    @Operation(summary = "프로모션 수정", description = "프로모션 수정을 한다.")
    @PutMapping("/{promotionTypeId}")
    public ResponseEntity<ApiResponse<Void>> updatePromotionType(
            @PathVariable(value = "promotionTypeId") Long id,
            @RequestBody UpdatePromotionTypeDTO updatePromotionTypeDTO
    ){

        promotionTypeService.updatePromotionType(id, updatePromotionTypeDTO);

        return ResponseUtil.successResponse(SuccessCode.PROMOTIONTYPE_UPDATE_SUCCESS);
    }

    @Operation(summary = "프로모션 종류 삭제", description = "프로모션 종류를 삭제한다.")
    @DeleteMapping("/{promotionId}")
    public ResponseEntity<ApiResponse<Void>> deletePromotionType(
            @PathVariable Long promotionId
    ) {

        promotionTypeService.deletePromotionType(promotionId);

        return ResponseUtil.successResponse(SuccessCode.PROMOTIONTYPE_DELETE_SUCCESS);
    }
}
