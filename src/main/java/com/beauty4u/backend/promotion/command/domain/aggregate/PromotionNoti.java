package com.beauty4u.backend.promotion.command.domain.aggregate;

import com.beauty4u.backend.customer.command.domain.aggregate.Customer;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "promotion_noti")
public class PromotionNoti {
    @Id
    @Column(name = "promotion_noti_id", nullable = false)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "promotion_setting_id", nullable = false)
    private PromotionSetting promotionSetting;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_code", nullable = false)
    private Customer customerCode;

    @NotNull
    @Lob
    @Column(name = "promotion_noti_content", nullable = false)
    private String promotionNotiContent;

    @NotNull
    @Column(name = "promotion_noti_sent_date", nullable = false)
    private Instant promotionNotiSentDate;

}