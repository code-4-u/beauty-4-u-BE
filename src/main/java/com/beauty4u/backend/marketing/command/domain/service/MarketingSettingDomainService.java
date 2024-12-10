package com.beauty4u.backend.marketing.command.domain.service;

import com.beauty4u.backend.marketing.command.application.dto.MarketingSettingReqDTO;
import com.beauty4u.backend.marketing.command.domain.aggregate.MarketingSetting;
import com.beauty4u.backend.marketing.command.domain.repository.MarketingSettingRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MarketingSettingDomainService {

    private final MarketingSettingRepository marketingSettingRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public void saveMarketingSetting(MarketingSettingReqDTO marketingSettingReqDTO) {
        MarketingSetting marketingSetting = modelMapper.map(marketingSettingReqDTO, MarketingSetting.class);

        marketingSettingRepository.save(marketingSetting);
    }
}
