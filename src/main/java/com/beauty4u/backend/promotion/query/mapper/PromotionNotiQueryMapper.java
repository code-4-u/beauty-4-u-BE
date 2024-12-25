package com.beauty4u.backend.promotion.query.mapper;

import com.beauty4u.backend.promotion.query.dto.FindPromotionByCustomerGoodsResDTO;
import com.beauty4u.backend.promotion.query.dto.FindPromotionResDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PromotionNotiQueryMapper {
    Integer findPromotionByCustomerGoodsCount(Integer promotionId);
    List<FindPromotionResDTO> findPromotion(String promotionName);
    List<FindPromotionByCustomerGoodsResDTO> findPromotionByCustomerGoods(Integer promotionId, Integer page, Integer count);
}
