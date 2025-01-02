package com.beauty4u.backend.goods_rate.query.mapper;

import com.beauty4u.backend.goods_rate.query.dto.GoodsRateQueryDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface GoodsRateQueryMapper {
    List<GoodsRateQueryDTO> findGoodsRate(
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate,
            @Param("periodType") String periodType
    );
}
