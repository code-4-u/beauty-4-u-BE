package com.beauty4u.backend.analysis.command.domain.aggregate;

import com.beauty4u.backend.customer.command.domain.aggregate.Customer;
import com.beauty4u.backend.goods.command.domain.aggregate.Goods;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "marketing_noti")
public class MarketingNoti {

    @Id
    @Column(name = "marketing_noti_id", nullable = false)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_code", nullable = false)
    private Customer customerCode;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "goods_code", nullable = false)
    private Goods goodsCode;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "marketing_setting_id", nullable = false)
    private MarketingSetting marketingSetting;

    @NotNull
    @Lob
    @Column(name = "marketing_noti_content", nullable = false)
    private String marketingNotiContent;

    @NotNull
    @Column(name = "marketing_noti_sent_date", nullable = false)
    private LocalDateTime marketingNotiSentDate;
}