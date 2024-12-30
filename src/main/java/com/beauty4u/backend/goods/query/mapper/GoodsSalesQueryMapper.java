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

    Long findSalesGoodsAge(
            @Param("goodsCode") String goodsCode,
            @Param("targetYear") int beforeYear,
            @Param("targetMonth") int month,
            @Param("startAge") int startAge,
            @Param("endAge") int endAge
    );
}
