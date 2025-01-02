package com.beauty4u.backend.goods.command.domain.aggregate;

import com.beauty4u.backend.common.aggregate.entity.CreatedTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
@Entity
@Table(name = "goods")
public class Goods extends CreatedTimeEntity {

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
}