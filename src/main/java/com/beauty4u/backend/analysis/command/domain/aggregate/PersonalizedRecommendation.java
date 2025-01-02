package com.beauty4u.backend.analysis.command.domain.aggregate;

import com.beauty4u.backend.customer.command.domain.aggregate.Customer;
import com.beauty4u.backend.goods.command.domain.aggregate.Goods;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "personalized_recommendation")
public class PersonalizedRecommendation {

    @Id
    @Column(name = "personalized_recommendation_id", nullable = false)
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
    @JoinColumn(name = "analysis_id", nullable = false)
    private Analysis analysis;

    @NotNull
    @Column(name = "recommendation_score", nullable = false)
    private Float recommendationScore;

    @Column(name = "last_noti_sent_date")
    private LocalDateTime lastNotiSentDate;
}