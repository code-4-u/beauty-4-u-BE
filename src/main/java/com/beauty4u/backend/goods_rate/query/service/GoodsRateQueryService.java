package com.beauty4u.backend.goods_rate.query.service;

import com.beauty4u.backend.goods_rate.query.dto.GoodsRateQueryDTO;
import com.beauty4u.backend.goods_rate.query.mapper.GoodsRateQueryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class GoodsRateQueryService {

    private final GoodsRateQueryMapper goodsRateQueryMapper;

    public List<GoodsRateQueryDTO> findGoodsRateIncrease() {
        return goodsRateQueryMapper.findGoodsRateIncrease();
    }
}
