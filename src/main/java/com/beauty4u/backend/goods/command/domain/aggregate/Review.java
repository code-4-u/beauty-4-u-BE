package com.beauty4u.backend.goods.command.domain.aggregate;

import com.beauty4u.backend.common.aggregate.entity.CreatedTimeEntity;
import com.beauty4u.backend.customer.command.domain.aggregate.Customer;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
@Entity
@Table(name = "review")
public class Review extends CreatedTimeEntity {

    @Id
    @Column(name = "review_id", nullable = false)
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
    @Column(name = "review_score", nullable = false)
    private Integer reviewScore;

    @NotNull
    @Lob
    @Column(name = "review_content", nullable = false)
    private String reviewContent;
}