package com.beauty4u.backend.goods.query.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface GoodsSalesQueryMapper {

    Long findGoodsMonthlySales(
            @Param("goodsCode") String goodsCode,
            @Param("month") Integer month,
            @Param("year") Integer year
    );
}
