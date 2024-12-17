package com.beauty4u.backend.marketing.command.domain.service;

import com.beauty4u.backend.common.exception.CustomException;
import com.beauty4u.backend.common.exception.ErrorCode;
import com.beauty4u.backend.marketing.command.application.dto.MarketSettingUpdateDTO;
import com.beauty4u.backend.marketing.command.application.dto.MarketingSettingReqDTO;
import com.beauty4u.backend.marketing.command.domain.aggregate.MarketingSetting;
import com.beauty4u.backend.marketing.command.domain.repository.MarketingSettingRepository;
import com.beauty4u.backend.template.command.domain.aggregate.Template;
import com.beauty4u.backend.template.command.domain.repository.TemplateRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MarketingSettingDomainService {

    private final TemplateRepository templateRepository;
    private final MarketingSettingRepository marketingSettingRepository;
    private final ModelMapper modelMapper;

    // 마케팅 알림 등록
    @Transactional
    public void saveMarketingSetting(MarketingSettingReqDTO marketingSettingReqDTO) {
        MarketingSetting marketingSetting = modelMapper.map(marketingSettingReqDTO, MarketingSetting.class);

        marketingSettingRepository.save(marketingSetting);
    }

    // 마케팅 알림 수정
    @Transactional
    public void updateMarketingSetting(Long marketingSettingId, MarketSettingUpdateDTO marketSettingUpdateDTO) {

        MarketingSetting marketingSetting = marketingSettingRepository.findById(marketingSettingId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_UPDATE_MARKETINGSETTING));

        Template template = templateRepository.findById(marketSettingUpdateDTO.getTemplateId())
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_TEMPLATE));

        marketingSetting.updateMarketingSetting(
                template,
                marketSettingUpdateDTO.getAnalysisKind(),
                marketSettingUpdateDTO.getMarketingNotiInterval(),
                marketSettingUpdateDTO.getMarketingChannelType()
        );
    }

    // 마케팅 알림 삭제
    @Transactional
    public void deleteMarketSetting(Long marketingSettingId) {

        MarketingSetting marketingSetting = marketingSettingRepository.findById(marketingSettingId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_MARKETINGSETTING));

        marketingSettingRepository.delete(marketingSetting);
    }
}
