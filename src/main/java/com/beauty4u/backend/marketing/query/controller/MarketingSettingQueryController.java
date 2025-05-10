package com.beauty4u.backend.marketing.query.controller;

import com.beauty4u.backend.common.response.ApiResponse;
import com.beauty4u.backend.common.response.ResponseUtil;
import com.beauty4u.backend.common.success.SuccessCode;
import com.beauty4u.backend.marketing.query.dto.MarketingSettingQueryDTO;
import com.beauty4u.backend.marketing.query.service.MarketingSettingQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/marketing")
@Tag(name="Marketing Setting", description = "마케팅 알림 설정 API")
public class MarketingSettingQueryController {

    private final MarketingSettingQueryService marketingSettingQueryService;

    @GetMapping("/list")
    @Operation(summary = "마케팅 목록 조회", description = "마케팅 전체 목록을 조회한다")
    public ResponseEntity<ApiResponse<List<MarketingSettingQueryDTO>>> findAllMarketingSetting(){

        List<MarketingSettingQueryDTO> marketingSettingList = marketingSettingQueryService.findAllMarketingSetting();

        return ResponseUtil.successResponse(SuccessCode.MARKETINGSETTING_FIND_LIST_SUCCESS, marketingSettingList);
    }
}
