package com.beauty4u.backend.goods.command.domain.repository;

import com.beauty4u.backend.goods.command.domain.aggregate.Goods;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoodsRepository extends JpaRepository<Goods, String> {
}
