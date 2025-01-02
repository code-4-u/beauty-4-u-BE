package com.beauty4u.backend.analysis.command.application.controller;

import com.beauty4u.backend.analysis.command.application.dto.AprioriRequest;
import com.beauty4u.backend.analysis.command.application.service.AprioriService;
import com.beauty4u.backend.common.response.ApiResponse;
import com.beauty4u.backend.common.response.ResponseUtil;
import com.beauty4u.backend.common.success.SuccessCode;
import com.beauty4u.backend.analysis.command.application.dto.AprioriResDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/apriori")
@RequiredArgsConstructor
@Tag(name = "Apriori", description = "연관분석")
public class AprioriController {

    private final AprioriService aprioriService;

    @Operation(summary = "연관 분석 후 조합 목록 조회", description = "연관 분석 후 조합 목록 조회")
    @PostMapping("/analyze")
    public ResponseEntity<ApiResponse<List<AprioriResDTO>>> analyzeProducts(
            @RequestBody AprioriRequest aprioriRequest
    ) {

        List<AprioriResDTO> aprioriResDTOS = aprioriService.analysisApriori(aprioriRequest);

        return ResponseUtil.successResponse(SuccessCode.APRIORI_FIND_SUCCESS, aprioriResDTOS);
    }
}
