package com.beauty4u.backend.promotion.command.domain.repository;

import com.beauty4u.backend.promotion.command.domain.aggregate.PromotionGoods;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PromotionGoodsRepository extends JpaRepository<PromotionGoods, Long> {
}
