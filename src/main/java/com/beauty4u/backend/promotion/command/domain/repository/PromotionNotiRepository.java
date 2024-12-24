package com.beauty4u.backend.promotion.command.domain.repository;

import com.beauty4u.backend.promotion.command.domain.aggregate.PromotionNoti;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PromotionNotiRepository extends JpaRepository<PromotionNoti, Long> {

}
