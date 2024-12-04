package com.beauty4u.backend.goods.query.mapper;

import com.beauty4u.backend.goods.query.dto.GoodsQueryDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GoodsQueryMapper {
    List<GoodsQueryDTO> findAllGoods();

    List<GoodsQueryDTO> findAllBrand(String brandCode);
}