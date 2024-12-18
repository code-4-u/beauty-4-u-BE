package com.beauty4u.backend.promotion.query.controller;

import com.beauty4u.backend.common.response.ApiResponse;
import com.beauty4u.backend.common.response.ResponseUtil;
import com.beauty4u.backend.common.success.SuccessCode;
import com.beauty4u.backend.promotion.query.dto.FindPromotionTypeListDTO;
import com.beauty4u.backend.promotion.query.dto.PromotionTypeFilterDTO;
import com.beauty4u.backend.promotion.query.service.PromotionTypeQueryService;
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
@RequestMapping("/api/v1/promotionType")
@Tag(name = "Promotion", description = "프로모션")
public class PromotionTypeQueryController {

    private final PromotionTypeQueryService promotionTypeQueryService;

    @Operation(summary = "프로모션 목록 조회", description = "프로모션 목록을 조회한다.")
    @GetMapping
    public ResponseEntity<ApiResponse<List<FindPromotionTypeListDTO>>> findPromotionTypeList(
            @RequestParam PromotionTypeFilterDTO promotionTypeFilterDTO
    ) {

        List<FindPromotionTypeListDTO> findPromotionTypeListDTOS = promotionTypeQueryService.findPromotionTypeList(promotionTypeFilterDTO);

        return ResponseUtil.successResponse(SuccessCode.PROMOTIONTYPE_LIST_FIND_SUCCESS, findPromotionTypeListDTOS);
    }
}