package com.beauty4u.backend.promotion.query.service;

import com.beauty4u.backend.promotion.query.dto.FindPromotionTypeDetailDTO;
import com.beauty4u.backend.promotion.query.dto.FindPromotionTypeListDTO;
import com.beauty4u.backend.promotion.query.dto.PromotionTypeFilterDTO;
import com.beauty4u.backend.promotion.query.dto.PromotionTypeList;
import com.beauty4u.backend.promotion.query.mapper.PromotionTypeQueryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PromotionTypeQueryService {

    private final PromotionTypeQueryMapper promotionTypeQueryMapper;

    @Transactional(readOnly = true)
    public PromotionTypeList findPromotionTypeList(PromotionTypeFilterDTO promotionTypeFilterDTO) {

        Long offset = null;

        if (promotionTypeFilterDTO != null) {
            offset = (promotionTypeFilterDTO.getPage() - 1) * promotionTypeFilterDTO.getCount();
        }

        List<FindPromotionTypeListDTO> promotionTypeDTOList = promotionTypeQueryMapper.findPromotionTypeList(
                promotionTypeFilterDTO.getPromotionTypeName(),
                promotionTypeFilterDTO.getSort(),
                promotionTypeFilterDTO.getOrder(),
                offset,
                promotionTypeFilterDTO.getCount()
        );

        PromotionTypeList promotionTypeList = new PromotionTypeList();
        promotionTypeList.setPromotionTypeList(promotionTypeDTOList);

        Long totalCount = promotionTypeQueryMapper.findPromotionTypeTotalCount(
                promotionTypeFilterDTO.getPromotionTypeName()
        );

        promotionTypeList.setTotalCount(totalCount);

        return promotionTypeList;
    }

    public FindPromotionTypeDetailDTO findPromotionTypeDetail(Long promotionTypeId) {

        return promotionTypeQueryMapper.findPromotionTypeDetail(promotionTypeId);
    }
}
