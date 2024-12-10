package com.beauty4u.backend.marketing.command.application.dto;

import com.beauty4u.backend.analysis.command.domain.aggregate.AnalysisKind;
import com.beauty4u.backend.common.aggregate.SettingChannelType;
import com.beauty4u.backend.template.command.domain.aggregate.Template;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MarketingSettingReqDTO {

    private Template templateId;
    private AnalysisKind analysisKind;
    private Integer marketingNotiInterval;
    private SettingChannelType marketingSettingChannel;
}
