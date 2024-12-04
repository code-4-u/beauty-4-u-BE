package com.beauty4u.backend.goods.query.mapper;

import com.beauty4u.backend.goods.query.dto.BrandQueryDTO;
import com.beauty4u.backend.goods.query.dto.GoodsQueryDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GoodsQueryMapper {
    // 전체 브랜드 조회
    List<BrandQueryDTO> findAllBrand();

    // 조건별 상품 목록 조회
    List<GoodsQueryDTO> findGoods(
            @Param("brandCode") String brandCode,
            @Param("goodsName") String goodsName
    );
}