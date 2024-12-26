package com.beauty4u.backend.goods.command.domain.repository;

import com.beauty4u.backend.goods.command.domain.aggregate.Goods;
import com.beauty4u.backend.goods.command.domain.aggregate.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByGoodsCode(Goods goodsCode);
}
