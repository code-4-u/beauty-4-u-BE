package com.beauty4u.backend.promotion.query.mapper;

import com.beauty4u.backend.promotion.query.dto.FindPromotionTypeDetailDTO;
import com.beauty4u.backend.promotion.query.dto.FindPromotionTypeListDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PromotionTypeQueryMapper {

    List<FindPromotionTypeListDTO> findPromotionTypeList(
            @Param("promotionTypeName") String promotionTypeName,
            @Param("sort") String sort,
            @Param("order") String order,
            @Param("offset") Long offset,
            @Param("count") Long count);

    FindPromotionTypeDetailDTO findPromotionTypeDetail(
            @Param("promotionTypeId") Long promotionTypeId);

    Long findPromotionTypeTotalCount(
            @Param("promotionTypeName") String promotionTypeName);
}
