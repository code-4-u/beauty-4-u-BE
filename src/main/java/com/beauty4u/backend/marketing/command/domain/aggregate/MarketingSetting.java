package com.beauty4u.backend.marketing.command.domain.aggregate;

import com.beauty4u.backend.analysis.command.domain.aggregate.Analysis;
import com.beauty4u.backend.analysis.command.domain.aggregate.AnalysisKind;
import com.beauty4u.backend.common.aggregate.SettingChannelType;
import com.beauty4u.backend.template.command.domain.aggregate.Template;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Entity
@Table(name = "marketing_setting", uniqueConstraints = {
        @UniqueConstraint(name = "uk_analysis_channel",
                          columnNames = {"analysis_kind", "marketing_setting_channel"})})
public class MarketingSetting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "marketing_setting_id")
    private Long marketingSettingId;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "analysis_id")
    private Analysis analysisId;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "template_id")
    private Template templateId;

    @NotNull
    @Column(name = "marketing_noti_interval")
    private Integer marketingNotiInterval;

    @NotNull
    @Column(name = "analysis_kind")
    @Enumerated(EnumType.STRING)
    private AnalysisKind analysisKind;

    @NotNull
    @Column(name = "marketing_setting_channel")
    @Enumerated(EnumType.STRING)
    private SettingChannelType marketingSettingChannel;

    public void updateMarketingSetting(Template templateId, AnalysisKind analysisKind, Integer marketingNotiInterval, SettingChannelType settingChannelType) {
        this.templateId = templateId;
        this.analysisKind = analysisKind;
        this.marketingNotiInterval = marketingNotiInterval;
        this.marketingSettingChannel = settingChannelType;

    }
}
