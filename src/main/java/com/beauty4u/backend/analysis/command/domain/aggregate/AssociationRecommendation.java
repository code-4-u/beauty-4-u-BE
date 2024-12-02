package com.beauty4u.backend.analysis.command.domain.aggregate;

import com.beauty4u.backend.customer.command.domain.aggregate.Customer;
import com.beauty4u.backend.goods.command.domain.aggregate.Goods;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "association_recommendation")
public class AssociationRecommendation {
    @Id
    @Column(name = "association_recommendation_id", nullable = false)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "goods_code", nullable = false)
    private Goods goodsCode;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "associated_goods_code", nullable = false)
    private Goods associatedGoodsCode;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_code", nullable = false)
    private Customer customerCode;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "analysis_id", nullable = false)
    private Analysis analysis;

    @NotNull
    @Column(name = "support", nullable = false)
    private Float support;

    @NotNull
    @Column(name = "confidence", nullable = false)
    private Float confidence;

    @Column(name = "last_noti_sent_date")
    private Instant lastNotiSentDate;

}