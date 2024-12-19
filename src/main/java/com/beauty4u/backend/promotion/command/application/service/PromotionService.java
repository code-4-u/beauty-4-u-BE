package com.beauty4u.backend.promotion.command.application.service;

import com.beauty4u.backend.common.exception.CustomException;
import com.beauty4u.backend.common.exception.ErrorCode;
import com.beauty4u.backend.promotion.command.application.dto.SavePromotionReqDTO;
import com.beauty4u.backend.promotion.command.domain.service.PromotionDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PromotionService {

    private final PromotionDomainService promotionDomainService;

    @Transactional
    public Long savePromotion(SavePromotionReqDTO savePromotionReqDTO) {

        return promotionDomainService.savePromotion(savePromotionReqDTO);
    }

    @Transactional
    public void updatePromotion(Long promotionId, SavePromotionReqDTO savePromotionReqDTO) {

        try {
            promotionDomainService.updatePromotion(promotionId, savePromotionReqDTO);
        } catch (Exception e) {
            throw new CustomException(ErrorCode.PROMOTION_NOT_UPDATE);
        }
    }

    @Transactional
    public void deletePromotion(Long promotionId) {

        promotionDomainService.deletePromotion(promotionId);
    }
}
