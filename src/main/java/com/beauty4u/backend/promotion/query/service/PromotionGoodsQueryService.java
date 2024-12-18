package com.beauty4u.backend.promotion.query.service;

import com.beauty4u.backend.common.exception.CustomException;
import com.beauty4u.backend.common.exception.ErrorCode;
import com.beauty4u.backend.promotion.query.dto.FindPromotionGoodsListFilterDTO;
import com.beauty4u.backend.promotion.query.dto.FindPromotionGoodsListResDTO;
import com.beauty4u.backend.promotion.query.mapper.PromotionGoodsQueryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PromotionGoodsQueryService {

    private final PromotionGoodsQueryMapper promotionGoodsQueryMapper;

    public List<FindPromotionGoodsListResDTO> findPromotionGoodsList(
            Long promotionId,
            FindPromotionGoodsListFilterDTO findPromotionGoodsListFilterDTO
    ) {

        Long offset = (findPromotionGoodsListFilterDTO.getPage() - 1) * findPromotionGoodsListFilterDTO.getCount();

        List<FindPromotionGoodsListResDTO> promotionGoodsList = null;

        try {
            promotionGoodsList = promotionGoodsQueryMapper.findPromotionGoodsList(
                    promotionId,
                    findPromotionGoodsListFilterDTO.getGoodsName(),
                    findPromotionGoodsListFilterDTO.getSort(),
                    findPromotionGoodsListFilterDTO.getOrder(),
                    offset,
                    findPromotionGoodsListFilterDTO.getCount()
            );
        } catch (Exception e) {
            throw new CustomException(ErrorCode.PROMOTION_GOODS_LIST_NOT_FOUND);
        }

        return promotionGoodsList;
    }
}
