package com.beauty4u.backend.promotion.query.service;

import com.beauty4u.backend.promotion.query.dto.FindPromotionByCustomerGoodsResDTO;
import com.beauty4u.backend.promotion.query.dto.FindPromotionResDTO;
import com.beauty4u.backend.promotion.query.mapper.PromotionNotiQueryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PromotionNotiQueryService {

    private final PromotionNotiQueryMapper promotionNotiQueryMapper;

    @Transactional
    public List<FindPromotionResDTO> findPromotion(String promotionName) {
        return promotionNotiQueryMapper.findPromotion(promotionName);
    }

    @Transactional
    public Integer findPromotionByCustomerGoodsCount(Integer promotionId){
        return promotionNotiQueryMapper.findPromotionByCustomerGoodsCount(promotionId);
    }

    @Transactional
    public List<FindPromotionByCustomerGoodsResDTO> findPromotionByCustomerGoods(Integer promotionId, Integer page, Integer count){
        return promotionNotiQueryMapper.findPromotionByCustomerGoods(promotionId, page, count);
    }
}