package com.beauty4u.backend.marketing.command.application.dto;

import com.beauty4u.backend.analysis.command.domain.aggregate.AnalysisKind;
import com.beauty4u.backend.common.aggregate.SettingChannelType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MarketSettingUpdateDTO {
    private Long analysisId;
    private Long templateId;
    private Integer marketingNotiInterval;
    @Enumerated(EnumType.STRING)
    private AnalysisKind analysisKind;
    @Enumerated(EnumType.STRING)
    private SettingChannelType marketingChannelType;
}
