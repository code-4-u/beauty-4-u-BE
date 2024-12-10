package com.beauty4u.backend.marketing.command.application.controller;

import com.beauty4u.backend.common.response.ApiResponse;
import com.beauty4u.backend.common.response.ResponseUtil;
import com.beauty4u.backend.common.success.SuccessCode;
import com.beauty4u.backend.marketing.command.application.dto.MarketingSettingReqDTO;
import com.beauty4u.backend.marketing.command.application.service.MarketingSettingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/marketingSetting")
@Tag(name = "Marketing Setting", description = "마케팅 알림 설정 관련 API")
public class MarketingSettingCommandController {

    private final MarketingSettingService marketingSettingService;

    @Operation(summary = "마케팅 알림 등록", description = "마케팅 알림을 등록한다.")
    @PostMapping
    public ResponseEntity<ApiResponse<MarketingSettingReqDTO>> saveMarketingSetting(
            @RequestBody @Valid MarketingSettingReqDTO marketingSettingReqDTO){

        marketingSettingService.saveMarketingSetting(marketingSettingReqDTO);

        return ResponseUtil.successResponse(SuccessCode.TEMPLATE_SAVE_SUCCESS, marketingSettingReqDTO);
    }



}
