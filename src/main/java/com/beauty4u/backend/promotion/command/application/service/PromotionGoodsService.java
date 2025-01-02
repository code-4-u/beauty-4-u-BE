package com.beauty4u.backend.promotion.command.application.service;

import com.beauty4u.backend.promotion.command.application.dto.DeletePromotionGoodsReqDTO;
import com.beauty4u.backend.promotion.command.application.dto.SavePromotionGoodsReqDTO;
import com.beauty4u.backend.promotion.command.application.dto.UpdatePromotionGoodsReqDTO;
import com.beauty4u.backend.promotion.command.domain.service.PromotionGoodsDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PromotionGoodsService {

    private final PromotionGoodsDomainService promotionGoodsDomainService;

    @Transactional
    public void savePromotionGoodsList(SavePromotionGoodsReqDTO savePromotionGoodsReqDTO) {

        promotionGoodsDomainService.savePromotionGoodsList(savePromotionGoodsReqDTO);
    }

    @Transactional
    public void deletePromotionGoodsList(DeletePromotionGoodsReqDTO deletePromotionGoodsReqDTO) {

        promotionGoodsDomainService.deletePromotionGoodsList(deletePromotionGoodsReqDTO);
    }

    @Transactional
    public void updatePromotionGoods(UpdatePromotionGoodsReqDTO updatePromotionGoodsReqDTO) {

        promotionGoodsDomainService.updatePromotionGoodsDiscount(updatePromotionGoodsReqDTO);
    }
}
