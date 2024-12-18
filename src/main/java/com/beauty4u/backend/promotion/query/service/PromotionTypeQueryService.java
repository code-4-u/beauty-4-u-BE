package com.beauty4u.backend.promotion.query.service;

import com.beauty4u.backend.promotion.query.dto.FindPromotionTypeListDTO;
import com.beauty4u.backend.promotion.query.dto.PromotionTypeFilterDTO;
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
    public List<FindPromotionTypeListDTO> findPromotionTypeList(PromotionTypeFilterDTO promotionTypeFilterDTO) {

        Long offset = null;

        if (promotionTypeFilterDTO != null) {
            offset = (promotionTypeFilterDTO.getPage() - 1) * promotionTypeFilterDTO.getCount();
        }

        return promotionTypeQueryMapper.findPromotionTypeList(
                promotionTypeFilterDTO.getPromotionTypeName(),
                promotionTypeFilterDTO.getSort(),
                promotionTypeFilterDTO.getOrder(),
                offset,
                promotionTypeFilterDTO.getCount()
        );
    }
}
