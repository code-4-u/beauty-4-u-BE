package com.beauty4u.backend.promotion.command.domain.repository;

import com.beauty4u.backend.promotion.command.domain.aggregate.PromotionNoti;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PromotionNotiRepository extends JpaRepository<PromotionNoti, Long> {
    @Query(value = """
        INSERT INTO promotion_noti (
            customer_code,
            promotion_noti_content,
            promotion_noti_sent_date
        )
        SELECT
               c.customer_code,
               CONCAT(c.customer_name, ' 고객님~ ',
                      p.promotion_title, ' 행사 맞이하여 추천상품 [',
                      g.goods_name, ']을 ',
                      pg.discount_rate, '% 할인된 가격으로 확인해보세요~!'),
               NOW()
          FROM customer c
          JOIN personalized_recommendation pr ON c.customer_code = pr.customer_code
          JOIN goods g ON g.goods_code = pr.goods_code
          JOIN promotion_goods pg ON pg.goods_code = pr.goods_code
          JOIN promotion p ON p.promotion_id = pg.promotion_id
          WHERE pg.promotion_id = :promotionId
        """, nativeQuery = true)
    void insertPromotionNotifications(@Param("promotionId") Long promotionId);
}
