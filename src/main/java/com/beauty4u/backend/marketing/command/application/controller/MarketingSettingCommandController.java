package com.beauty4u.backend.marketing.command.application.controller;

import com.beauty4u.backend.marketing.command.application.service.MarketingSettingService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/marketingSetting")
@Tag(name = "Marketing Setting", description = "마케팅 설정 관련 API")
public class MarketingSettingCommandController {

    private final MarketingSettingService marketingSettingService;



}
