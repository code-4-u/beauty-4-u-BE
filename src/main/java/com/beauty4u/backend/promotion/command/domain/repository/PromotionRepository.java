package com.beauty4u.backend.promotion.command.domain.repository;

import com.beauty4u.backend.promotion.command.domain.aggregate.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PromotionRepository extends JpaRepository<Promotion, Long> {
}
