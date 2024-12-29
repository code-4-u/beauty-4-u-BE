package com.beauty4u.backend.analysis.command.application.controller;

import com.beauty4u.backend.analysis.command.application.dto.CollaboFilterResDTO;
import com.beauty4u.backend.analysis.command.application.service.CollaboFilterService;
import com.beauty4u.backend.common.response.ApiResponse;
import com.beauty4u.backend.common.response.ResponseUtil;
import com.beauty4u.backend.common.success.SuccessCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/collaboFilter")
@Tag(name = "collaboFilter", description = "협업필터링 고객별 맞춤 추천")
public class CollaboFilterController {

    private final CollaboFilterService collaboFilterService;

    @Operation(summary = "협업 필터링 고객별 맞춤 추천", description = "맞춤 추천을 실행 후 DB에 저장해준다.")
    @GetMapping("/goods")
    public ResponseEntity<ApiResponse<CollaboFilterResDTO>> runningCollaboFilter() {

        CollaboFilterResDTO collaboFilterResDTO = collaboFilterService.runCollaboFilter();

        return ResponseUtil.successResponse(SuccessCode.CUSTOM_ANALYSIS_RUN_SUCCESS, collaboFilterResDTO);
    }

}


