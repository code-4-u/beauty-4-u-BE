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

    @NotNull
    private Long analysisId;

    @NotNull
    private Long templateId;

    @NotNull
    private Integer marketingNotiInterval;

    @NotNull
    @Enumerated(EnumType.STRING)
    private AnalysisKind analysisKind;

    @NotNull
    @Enumerated
    private SettingChannelType settingChannelType;
}
