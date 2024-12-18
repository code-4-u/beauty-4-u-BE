package com.beauty4u.backend.promotion.query.mapper;

import com.beauty4u.backend.promotion.query.dto.FindPromotionGoodsCommonInfoResDTO;
import com.beauty4u.backend.promotion.query.dto.FindPromotionGoodsListResDTO;
import com.beauty4u.backend.promotion.query.dto.FindPromotionGoodsSalesDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
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

    List<FindPromotionGoodsSalesDTO> findPromotionGoodsCommonInfoList(
            @Param("goodsCode") String goodsCode,
            @Param("year") Integer year,
            @Param("month") Integer month,
            @Param("promotionTitle") String promotionTitle,
            @Param("sort") String sort,
            @Param("order") String order,
            @Param("offset") Long offset,
            @Param("count") Long count
    );

    Long findPromotionGoodsSales(
            @Param("promotionStartDate") LocalDateTime promotionStartDate,
            @Param("promotionEndDate") LocalDateTime promotionEndDate
    );

    Long findPromotionGoodsAvgSales(
            @Param("oneYearAgo") LocalDateTime oneYearAgo,
            @Param("promotionStartDate") LocalDateTime promotionStartDate
    );
}
