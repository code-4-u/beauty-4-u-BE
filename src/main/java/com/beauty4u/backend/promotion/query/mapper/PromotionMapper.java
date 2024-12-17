package com.beauty4u.backend.promotion.query.mapper;

import com.beauty4u.backend.promotion.query.dto.PromotionDetailResDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface PromotionMapper {

    PromotionDetailResDTO findPromotionById(@Param("promotionId") Long promotionId);

    List<PromotionDetailResDTO> findPromotionList(
            @Param("promotionTitle") String promotionTitle,
            @Param("startDate") LocalDateTime promotionStartDate,
            @Param("endDate") LocalDateTime promotionEndDate,
            @Param("promotionStatus") String promotionStatus,
            @Param("sort") String sort,
            @Param("order") String order,
            @Param("offset") Long offset,
            @Param("count") Long count);
}
