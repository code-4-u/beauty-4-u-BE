package com.beauty4u.backend.marketing.command.application.service;

import com.beauty4u.backend.marketing.command.application.dto.MarketSettingUpdateDTO;
import com.beauty4u.backend.marketing.command.application.dto.MarketingSettingReqDTO;
import com.beauty4u.backend.marketing.command.domain.service.MarketingSettingDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MarketingSettingService {

    private final MarketingSettingDomainService marketingSettingDomainService;

    // 마케팅 알림 등록
    public void saveMarketingSetting(MarketingSettingReqDTO marketingSettingReqDTO) {

        marketingSettingDomainService.saveMarketingSetting(marketingSettingReqDTO);
    }

    // 마케팅 알림 수정
    public void updateMarketingSetting(Long marketingSettingId, MarketSettingUpdateDTO marketSettingUpdateDTO) {

        marketingSettingDomainService.updateMarketingSetting(marketingSettingId, marketSettingUpdateDTO);
    }

    // 마케팅 알림 삭제
    public void deleteMarketingSetting(Long marketingSettingId) {

        marketingSettingDomainService.deleteMarketSetting(marketingSettingId);
    }
}
