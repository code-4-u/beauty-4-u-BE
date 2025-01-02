package com.beauty4u.backend.goods.command.domain.aggregate;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "sub_category")
public class SubCategory {
    @Id
    @Size(max = 20)
    @Column(name = "sub_category_code", nullable = false, length = 20)
    private String subCategoryCode;

    @Size(max = 20)
    @NotNull
    @Column(name = "top_category_code", nullable = false)
    private String topCategoryCode;

    @Size(max = 50)
    @NotNull
    @Column(name = "sub_category_name", nullable = false, length = 50)
    private String subCategoryName;

}