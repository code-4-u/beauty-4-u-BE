package com.beauty4u.backend.promotion.command.domain.aggregate;

import com.beauty4u.backend.goods.command.domain.aggregate.Goods;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "promotion_goods")
public class PromotionGoods {
    @Id
    @Column(name = "promotion_goods_id", nullable = false)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "promotion_id", nullable = false)
    private Promotion promotion;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "goods_code", nullable = false)
    private Goods goodsCode;

    @NotNull
    @Column(name = "discount_rate", nullable = false)
    private Integer discountRate;

}