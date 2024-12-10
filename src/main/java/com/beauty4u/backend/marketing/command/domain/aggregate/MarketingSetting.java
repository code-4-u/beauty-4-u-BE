package com.beauty4u.backend.marketing.command.domain.aggregate;

import com.beauty4u.backend.analysis.command.domain.aggregate.Analysis;
import com.beauty4u.backend.analysis.command.domain.aggregate.AnalysisKind;
import com.beauty4u.backend.common.aggregate.SettingChannelType;
import com.beauty4u.backend.template.command.domain.aggregate.Template;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
@Entity
@Table(name = "marketing_setting")
public class MarketingSetting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "marketing_setting_id", nullable = false)
    private Long marketingSettingId;

    @NotNull
    @OneToOne
    @JoinColumn(name = "analysis_id")
    private Analysis analysisId;

    @NotNull
    @OneToOne
    @JoinColumn(name = "template_id")
    private Template templateId;

    @NotNull
    @Column(name = "marketing_noti_interval", nullable = false)
    private Integer marketingNotiInterval;

    @NotNull
    @Column(name = "analysis_kind", nullable = false)
    @Enumerated(EnumType.STRING)
    private AnalysisKind analysisKind;

    @NotNull
    @Column(name = "marketing_setting_channel")
    @Enumerated(EnumType.STRING)
    private SettingChannelType marketingSettingChannel;

}
