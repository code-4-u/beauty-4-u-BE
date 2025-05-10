package com.beauty4u.backend.marketing.command.application.dto;

import com.beauty4u.backend.analysis.command.domain.aggregate.AnalysisKind;
import com.beauty4u.backend.common.aggregate.SettingChannelType;
import com.beauty4u.backend.template.command.domain.aggregate.Template;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MarketingSettingReqDTO {
    private Long analysisId;
    private Long templateId;
    private Integer marketingNotiInterval;
    @Enumerated(EnumType.STRING)
    private AnalysisKind analysisKind;
    @Enumerated(EnumType.STRING)
    private SettingChannelType settingChannelType;
}
