package com.beauty4u.backend.promotion.query.mapper;

import com.beauty4u.backend.promotion.query.dto.FindPromotionByCustomerGoodsResDTO;
import com.beauty4u.backend.promotion.query.dto.FindPromotionNotiTargetResDTO;
import com.beauty4u.backend.promotion.query.dto.FindPromotionResDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PromotionNotiQueryMapper {
    Integer findPromotionByCustomerGoodsCount(Integer promotionId);
    Integer findPromotionAnalysisId();
    Integer promotionCount(String promotionName);
    List<FindPromotionResDTO> findPromotion(String promotionName, Integer page, Integer count);
    List<FindPromotionNotiTargetResDTO> findPromotionNotiTarget(Long promotionId, Long analysisId);
    List<FindPromotionByCustomerGoodsResDTO> findPromotionByCustomerGoods(Integer promotionId, Integer page, Integer count);
}
