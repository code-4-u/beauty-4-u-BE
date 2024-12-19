package com.beauty4u.backend.promotion.command.domain.service;

import com.beauty4u.backend.common.exception.CustomException;
import com.beauty4u.backend.common.exception.ErrorCode;
import com.beauty4u.backend.goods.command.domain.aggregate.Goods;
import com.beauty4u.backend.goods.command.domain.repository.GoodsRepository;
import com.beauty4u.backend.promotion.command.application.dto.*;
import com.beauty4u.backend.promotion.command.domain.aggregate.Promotion;
import com.beauty4u.backend.promotion.command.domain.aggregate.PromotionGoods;
import com.beauty4u.backend.promotion.command.domain.repository.PromotionGoodsRepository;
import com.beauty4u.backend.promotion.command.domain.repository.PromotionRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PromotionGoodsDomainService {

    private final PromotionGoodsRepository promotionGoodsRepository;
    private final GoodsRepository goodsRepository;
    private final PromotionRepository promotionRepository;
    private final ModelMapper modelMapper;

    public void savePromotionGoodsList(SavePromotionGoodsReqDTO savePromotionGoodsReqDTO) {

        List<PromotionGoods> promotionGoodsList = new ArrayList<>();

        Promotion promotion = promotionRepository.findById(savePromotionGoodsReqDTO.getPromotionId())
                .orElseThrow(() -> new CustomException(ErrorCode.PROMOTION_NOT_FOUND));

        for (SaveGoodsDiscountDTO saveGoodsDiscountDTO : savePromotionGoodsReqDTO.getSaveGoodsDiscountDTOS()) {
            Goods goods = goodsRepository.findById(saveGoodsDiscountDTO.getGoodsCode())
                    .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_GOODS));

            PromotionGoods promotionGoods = modelMapper.map(
                    new SavePromotionGoodsDTO(
                            promotion, goods, saveGoodsDiscountDTO.getDiscountRate()), PromotionGoods.class);

            promotionGoodsList.add(promotionGoods);
        }

        try {
            promotionGoodsRepository.saveAll(promotionGoodsList);
        } catch (Exception e) {
            throw new CustomException(ErrorCode.PROMOTION_GOODS_SAVE_FAIL);
        }
    }

    public void deletePromotionGoodsList(DeletePromotionGoodsReqDTO deletePromotionGoodsReqDTO) {

        try {
            for (Long id : deletePromotionGoodsReqDTO.getPromotionGoodsIdList()) {

                promotionGoodsRepository.deleteById(id);
            }
        } catch (Exception e) {
            throw new CustomException(ErrorCode.PROMOTION_GOODS_LIST_DELETE_FAIL);
        }
    }

    public void updatePromotionGoodsDiscount(UpdatePromotionGoodsReqDTO updatePromotionGoodsReqDTO) {

        try {

            for (UpdatePromotionGoodsListReqDTO promotionGoods : updatePromotionGoodsReqDTO.getPromotionGoodsList()) {
                PromotionGoods findPromotionGoods = promotionGoodsRepository.findById(promotionGoods.getPromotionGoodsId())
                        .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_PROMOTION_GOODS));

                findPromotionGoods.modifyDiscountRate(promotionGoods.getDiscountRate());
            }
        } catch (Exception e) {
            throw new CustomException(ErrorCode.PROMOTION_GOODS_UPDATE_FAIL);
        }
    }
}
