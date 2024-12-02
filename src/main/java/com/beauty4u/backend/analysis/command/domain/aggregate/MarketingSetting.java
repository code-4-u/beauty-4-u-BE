package com.beauty4u.backend.analysis.command.domain.aggregate;

import com.beauty4u.backend.basesystem.command.domain.aggregate.Template;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "marketing_setting")
public class MarketingSetting {
    @Id
    @Column(name = "marketing_setting_id", nullable = false)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "analysis_id", nullable = false)
    private Analysis analysis;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "template_id", nullable = false)
    private Template template;

    @NotNull
    @Column(name = "marketing_noti_interval", nullable = false)
    private Integer marketingNotiInterval;

    @NotNull
    @Lob
    @Column(name = "analysis_kind", nullable = false)
    private String analysisKind;

    @NotNull
    @Lob
    @Column(name = "marketing_setting_channel", nullable = false)
    private String marketingSettingChannel;

}