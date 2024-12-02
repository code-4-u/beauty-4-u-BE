package com.beauty4u.backend.customer.command.domain.aggregate;

import com.beauty4u.backend.goods.command.domain.aggregate.Goods;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "order_info")
public class OrderInfo {
    @Id
    @Column(name = "order_id", nullable = false)
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
    @Column(name = "order_count", nullable = false)
    private Integer orderCount;

    @NotNull
    @Column(name = "order_price", nullable = false)
    private Integer orderPrice;

    @NotNull
    @Lob
    @Column(name = "order_status", nullable = false)
    private String orderStatus;

    @NotNull
    @Column(name = "order_created_date", nullable = false)
    private Instant orderCreatedDate;

}