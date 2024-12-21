package com.beauty4u.backend.promotion.query.mapper;

import com.beauty4u.backend.promotion.query.dto.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PromotionStatisticalMapper {

    /* 프로모션 종류 조회 */
    List<FindPromotionTypeResDTO> findPromotionType();

    /* 프로모션 검색 */
    List<FindPromotionResDTO> findPromotion(FindPromotionReqDTO findPromotionReqDTO);

    /* 프로모션 타입별 조회 ASC 조회 */
    List<FindPromotionByTypeResDTO> findTypeByPromotion();

    /* 프로모션 종류에 따른 년도별 그래프 조회 */
    List<FindPromotionByYearSalesResDTO> findPromotionByYearSales(List<Integer> promotionIds);

    /* 프로모션 기간 상품 매출 랭킹 비교 */
    List<FindPromotionByComparisonSalesResDTO> findPromotionByComparisonSales(Integer previousPromotionId, Integer afterPromotionId);
}
