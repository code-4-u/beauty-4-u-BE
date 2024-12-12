package com.beauty4u.backend.promotion.command.domain.aggregate;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "promotion")
public class Promotion {

    @Id
    @Column(name = "promotion_id", nullable = false)
    private Long id;

    @Size(max = 100)
    @NotNull
    @Column(name = "promotion_title", nullable = false, length = 100)
    private String promotionTitle;

    @Size(max = 255)
    @NotNull
    @Column(name = "promotion_content", nullable = false)
    private String promotionContent;

    @NotNull
    @Column(name = "promotion_start_date", nullable = false)
    private LocalDateTime promotionStartDate;

    @NotNull
    @Column(name = "promotion_end_date", nullable = false)
    private LocalDateTime promotionEndDate;

    @NotNull
    @Lob
    @Column(name = "promotion_status", nullable = false)
    private String promotionStatus;
}