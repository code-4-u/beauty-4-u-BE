package com.beauty4u.backend.marketing.command.application.service;

import com.beauty4u.backend.marketing.command.application.dto.MarketingSettingReqDTO;
import com.beauty4u.backend.marketing.command.domain.service.MarketingSettingDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MarketingSettingService {

    private final MarketingSettingDomainService marketingSettingDomainService;

    public void saveMarketingSetting(MarketingSettingReqDTO marketingSettingReqDTO) {

        marketingSettingDomainService.saveMarketingSetting(marketingSettingReqDTO);
    }
}
