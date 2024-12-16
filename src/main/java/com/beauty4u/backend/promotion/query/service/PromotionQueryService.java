package com.beauty4u.backend.promotion.query.service;

import com.beauty4u.backend.common.exception.CustomException;
import com.beauty4u.backend.common.exception.ErrorCode;
import com.beauty4u.backend.promotion.query.dto.PromotionDetailResDTO;
import com.beauty4u.backend.promotion.query.mapper.PromotionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PromotionQueryService {

    private final PromotionMapper promotionMapper;

    public PromotionDetailResDTO findPromotionDetail(Long promotionId) {

        PromotionDetailResDTO findPromotionDTO = null;
        try {
            findPromotionDTO = promotionMapper.findPromotionById(promotionId);
        } catch (Exception e) {
            throw new CustomException(ErrorCode.PROMOTION_FIND_DETAIL_FAIL);
        }

        return findPromotionDTO;
    }
}
