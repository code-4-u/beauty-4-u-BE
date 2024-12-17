package com.beauty4u.backend.marketing.query.dto;

import com.beauty4u.backend.analysis.command.domain.aggregate.AnalysisKind;
import com.beauty4u.backend.common.aggregate.SettingChannelType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MarketingSettingQueryDTO {
    private Long marketingSettingId;
    private String templateName;
    private String analysisTitle;
    private Integer marketingNotiInterval;
    private AnalysisKind analysisKind;
    private SettingChannelType marketingSettingChannel;
}
