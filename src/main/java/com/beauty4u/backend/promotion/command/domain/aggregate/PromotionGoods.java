package com.beauty4u.backend.promotion.command.domain.aggregate;

import com.beauty4u.backend.goods.command.domain.aggregate.Goods;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
@Entity
@Table(name = "promotion_goods",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_promotion_goods", // 제약조건 이름
                        columnNames = {"promotion_id", "goods_code"} // 유니크해야 하는 컬럼들
                )
        }
)
public class PromotionGoods {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public void modifyDiscountRate(Integer discountRate) {
        this.discountRate = discountRate;
    }
}