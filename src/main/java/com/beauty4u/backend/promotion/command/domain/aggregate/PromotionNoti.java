package com.beauty4u.backend.promotion.command.domain.aggregate;

import com.beauty4u.backend.customer.command.domain.aggregate.Customer;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "promotion_noti")
public class PromotionNoti {

    @Id
    @Column(name = "promotion_noti_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // 이 부분을 추가
    private Long id;

    @NotNull
    @Column(name = "customer_code")
    private String customerCode;

    @NotNull
    @Lob
    @Column(name = "promotion_noti_content", nullable = false)
    private String promotionNotiContent;

    @NotNull
    @Column(name = "promotion_noti_sent_date", nullable = false)
    private LocalDateTime promotionNotiSentDate;
}