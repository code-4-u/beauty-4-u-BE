package com.beauty4u.backend.goods.command.domain.aggregate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "top_category")
public class TopCategory {
    @Id
    @Size(max = 20)
    @Column(name = "top_category_code", nullable = false, length = 20)
    private String topCategoryCode;

    @Size(max = 50)
    @NotNull
    @Column(name = "top_category_name", nullable = false, length = 50)
    private String topCategoryName;

}