package com.beauty4u.backend.goods_rate.query.mapper;

import com.beauty4u.backend.goods_rate.query.dto.GoodsRateQueryDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GoodsRateQueryMapper {
    List<GoodsRateQueryDTO> findGoodsRateIncrease();
}
