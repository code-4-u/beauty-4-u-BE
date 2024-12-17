package com.beauty4u.backend.marketing.command.application.dto;

import com.beauty4u.backend.analysis.command.domain.aggregate.AnalysisKind;
import com.beauty4u.backend.common.aggregate.SettingChannelType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MarketingSettingDTO {
    private Long marketingSettingsId;
    private Long templateId;
    private Integer marketingNotiInterval;
    private AnalysisKind analysisKind;
    private SettingChannelType marketingChannelType;
}
