package com.beauty4u.backend.promotion.command.domain.aggregate;

import com.beauty4u.backend.template.command.domain.aggregate.Template;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "promotion_setting")
public class PromotionSetting {
    @Id
    @Column(name = "promotion_setting_id", nullable = false)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "promotion_id", nullable = false)
    private Promotion promotion;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "template_id", nullable = false)
    private Template template;

    @NotNull
    @Column(name = "promotion_noti_interval", nullable = false)
    private Integer promotionNotiInterval;

    @NotNull
    @Column(name = "last_noti_sent_date", nullable = false)
    private Instant lastNotiSentDate;

    @NotNull
    @Column(name = "promotion_setting_start_date", nullable = false)
    private Instant promotionSettingStartDate;

    @NotNull
    @Column(name = "promotion_setting_end_date", nullable = false)
    private Instant promotionSettingEndDate;

    @NotNull
    @Lob
    @Column(name = "promotion_setting_channel", nullable = false)
    private String promotionSettingChannel;

}