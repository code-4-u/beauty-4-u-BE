package com.beauty4u.backend.marketing.command.application.controller;

import com.beauty4u.backend.common.response.ApiResponse;
import com.beauty4u.backend.common.response.ResponseUtil;
import com.beauty4u.backend.common.success.SuccessCode;
import com.beauty4u.backend.marketing.command.application.dto.MarketSettingUpdateDTO;
import com.beauty4u.backend.marketing.command.application.dto.MarketingSettingDTO;
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
@RequestMapping("/api/v1/marketing")
@Tag(name = "Marketing Setting", description = "마케팅 알림 설정 API")
public class MarketingSettingCommandController {

    private final MarketingSettingService marketingSettingService;

    @Operation(summary = "마케팅 알림 등록", description = "마케팅 알림을 등록한다.")
    @PostMapping("/set")
    public ResponseEntity<ApiResponse<MarketingSettingDTO>> saveMarketingSetting(
            @RequestBody @Valid MarketingSettingReqDTO marketingSettingReqDTO) {

        marketingSettingService.saveMarketingSetting(marketingSettingReqDTO);

        return ResponseUtil.successResponse(SuccessCode.MARKETINGSETTING_SAVE_SUCCESS);
    }

    @Operation(summary = "마케팅 알림 수정", description = "마케팅 알림을 수정한다")
    @PutMapping("/set/{marketingSettingId}")
    public ResponseEntity<ApiResponse<MarketingSettingDTO>> updateMarketingSetting(
            @PathVariable Long marketingSettingId,
            @RequestBody @Valid MarketSettingUpdateDTO marketSettingUpdateDTO) {

        marketingSettingService.updateMarketingSetting(marketingSettingId, marketSettingUpdateDTO);

        return ResponseUtil.successResponse(SuccessCode.MARKETINGSETTING_UPDATE_SUCCESS);

    }

    @Operation(summary = "마케팅 알림 삭제", description = "마케팅 알림을 삭제한다")
    @DeleteMapping("/set/{marketingSettingId}")
    public ResponseEntity<ApiResponse<Void>> deleteMarketingSetting(
            @PathVariable Long marketingSettingId){

        marketingSettingService.deleteMarketingSetting(marketingSettingId);

        return ResponseUtil.successResponse(SuccessCode.MARKETINGSETTING_DELETE_SUCCESS);
    }
}
