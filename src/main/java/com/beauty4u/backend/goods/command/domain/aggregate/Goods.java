package com.beauty4u.backend.goods.command.domain.aggregate;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "goods")
public class Goods {
    @Id
    @Size(max = 20)
    @Column(name = "goods_code", nullable = false, length = 20)
    private String goodsCode;

    @Size(max = 20)
    @NotNull
    @Column(name = "brand_code", nullable = false)
    private String brandCode;

    @Size(max = 20)
    @NotNull
    @Column(name = "sub_category_code", nullable = false)
    private String subCategoryCode;

    @Size(max = 50)
    @NotNull
    @Column(name = "goods_name", nullable = false, length = 50)
    private String goodsName;

    @NotNull
    @Column(name = "goods_price", nullable = false)
    private Integer goodsPrice;

    @Size(max = 20)
    @NotNull
    @Column(name = "goods_skintype", nullable = false, length = 20)
    private String goodsSkintype;

    @NotNull
    @Column(name = "goods_created_date", nullable = false)
    private Instant goodsCreatedDate;

}