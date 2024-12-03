package com.beauty4u.backend.goods.query.repository;

import com.beauty4u.backend.goods.query.dto.FindGoodsDTO;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class GoodsQueryRepository {
    private final JPAQueryFactory jpaQueryFactory;

    public List<FindGoodsDTO> findAllGoods(){

    }
}