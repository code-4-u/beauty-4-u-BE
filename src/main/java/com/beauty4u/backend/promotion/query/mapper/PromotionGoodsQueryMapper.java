package com.beauty4u.backend.promotion.query.mapper;

import com.beauty4u.backend.promotion.query.dto.FindPromotionGoodsListResDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PromotionGoodsQueryMapper {

    List<FindPromotionGoodsListResDTO> findPromotionGoodsList(
            @Param("promotionId") Long promotionId,
            @Param("goodsName") String goodsName,
            @Param("sort") String sort,
            @Param("order") String order,
            @Param("offset") Long offset,
            @Param("count") Long count
    );
}
