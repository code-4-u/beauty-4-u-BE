package com.beauty4u.backend.promotion.command.domain.repository;

import com.beauty4u.backend.promotion.command.domain.aggregate.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PromotionRepository extends JpaRepository<Promotion, Long> {

    @Query("SELECT COUNT(p) > 0 FROM Promotion p " +
            "WHERE p.promotionType.id = :promotionTypeId " +
            "AND FUNCTION('YEAR', p.promotionStartDate) = :promotionYear")
    Boolean existsPromotionInSameYear(
            @Param("promotionTypeId") Long promotionTypeId,
            @Param("promotionYear") int promotionYear);
}
