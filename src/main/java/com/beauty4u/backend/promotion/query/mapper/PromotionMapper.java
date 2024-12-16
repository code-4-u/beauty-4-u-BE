package com.beauty4u.backend.promotion.query.mapper;

import com.beauty4u.backend.promotion.query.dto.PromotionDetailResDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PromotionMapper {

    PromotionDetailResDTO findPromotionById(@Param("promotionId") Long promotionId);
}
