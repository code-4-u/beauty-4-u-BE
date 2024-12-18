package com.beauty4u.backend.promotion.query.service;

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

        return promotionGoodsQueryMapper.findPromotionGoodsList(
                promotionId,
                findPromotionGoodsListFilterDTO.getGoodsName(),
                findPromotionGoodsListFilterDTO.getSort(),
                findPromotionGoodsListFilterDTO.getOrder(),
                offset,
                findPromotionGoodsListFilterDTO.getCount()
        );
    }
}
