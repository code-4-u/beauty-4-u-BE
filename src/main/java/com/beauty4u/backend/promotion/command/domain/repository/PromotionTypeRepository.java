package com.beauty4u.backend.promotion.command.domain.repository;

import com.beauty4u.backend.promotion.command.domain.aggregate.PromotionType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PromotionTypeRepository extends JpaRepository<PromotionType, Long> {
}
