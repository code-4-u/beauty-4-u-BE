package com.beauty4u.backend.promotion.query.service;

import com.beauty4u.backend.promotion.query.dto.*;
import com.beauty4u.backend.promotion.query.mapper.PromotionStatisticalMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PromotionStatisticalQueryService {

    private final PromotionStatisticalMapper promotionStatisticalMapper;

    /* 프로모션 종류 조회 */
    @Transactional
    public List<FindPromotionTypeResDTO> findPromotionType() {
        return promotionStatisticalMapper.findPromotionType();
    }

    /* 프로모션 검색 기능 */
    @Transactional
    public List<FindPromotionResDTO> findPromotion(FindPromotionReqDTO findPromotionReqDTO){
        return promotionStatisticalMapper.findPromotion(findPromotionReqDTO);
    }

    /* 프로모션 타입별 조회 ASC 조회 */
    @Transactional
    public List<FindPromotionByTypeResDTO> findTypeByPromotion(){
        return promotionStatisticalMapper.findTypeByPromotion();
    }

    /* 프로모션 종류에 따른 년도별 그래프 조회 */
    @Transactional
    public List<FindPromotionByYearSalesResDTO> findPromotionByYearSales(List<Integer> promotionIds){
        return promotionStatisticalMapper.findPromotionByYearSales(promotionIds);
    }

    /* 프로모션 기간 상품 매출 랭킹 비교 */
    @Transactional
    public List<FindPromotionByComparisonSalesResDTO> findPromotionByComparisonSales(Integer previousPromotionId, Integer afterPromotionId){
        return promotionStatisticalMapper.findPromotionByComparisonSales(previousPromotionId, afterPromotionId);
    }
}
