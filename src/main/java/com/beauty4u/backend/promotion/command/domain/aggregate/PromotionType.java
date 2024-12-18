package com.beauty4u.backend.promotion.command.domain.aggregate;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Setter;

@Setter
@Entity
@Table(name = "promotion_type", schema = "beautydb")
public class PromotionType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "promotion_type_id", nullable = false)
    private Long id;

    @Size(max = 100)
    @NotNull
    @Column(name = "promotion_type_name", nullable = false, length = 100)
    private String promotionTypeName;

}