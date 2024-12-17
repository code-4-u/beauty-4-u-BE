package com.beauty4u.backend.promotion.command.application.service;

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
    public void savePromotion(SavePromotionReqDTO savePromotionReqDTO) {

        promotionDomainService.savePromotion(savePromotionReqDTO);
    }

    @Transactional
    public void updatePromotion(Long promotionId, SavePromotionReqDTO savePromotionReqDTO) {

        promotionDomainService.updatePromotion(promotionId, savePromotionReqDTO);
    }
}
