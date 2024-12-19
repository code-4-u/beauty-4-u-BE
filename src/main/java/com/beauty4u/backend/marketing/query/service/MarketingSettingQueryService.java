package com.beauty4u.backend.marketing.query.service;

import com.beauty4u.backend.marketing.query.dto.MarketingSettingQueryDTO;
import com.beauty4u.backend.marketing.query.mapper.MarketingSettingQueryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class MarketingSettingQueryService {
    private final MarketingSettingQueryMapper marketingSettingQueryMapper;

    public List<MarketingSettingQueryDTO> findAllMarketingSetting() {

        return marketingSettingQueryMapper.findAllMarketingSetting();
    }
}
