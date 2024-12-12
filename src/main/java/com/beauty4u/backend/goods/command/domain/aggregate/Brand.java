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
@Table(name = "brand")
public class Brand extends CreatedTimeEntity {

    @Id
    @Size(max = 20)
    @Column(name = "brand_code", nullable = false, length = 20)
    private String brandCode;

    @Size(max = 50)
    @NotNull
    @Column(name = "brand_name", nullable = false, length = 50)
    private String brandName;
}