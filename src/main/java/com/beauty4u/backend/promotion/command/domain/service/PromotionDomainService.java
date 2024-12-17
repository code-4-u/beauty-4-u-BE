package com.beauty4u.backend.promotion.command.domain.service;

import com.beauty4u.backend.common.exception.CustomException;
import com.beauty4u.backend.common.exception.ErrorCode;
import com.beauty4u.backend.promotion.command.application.dto.SavePromotionReqDTO;
import com.beauty4u.backend.promotion.command.domain.aggregate.Promotion;
import com.beauty4u.backend.promotion.command.domain.repository.PromotionRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PromotionDomainService {

    private final PromotionRepository promotionRepository;
    private final ModelMapper modelMapper;

    public void savePromotion(SavePromotionReqDTO savePromotionReqDTO) {

        Promotion promotion = modelMapper.map(savePromotionReqDTO, Promotion.class);

        try {
            promotionRepository.save(promotion);
        } catch (Exception e) {
            throw new CustomException(ErrorCode.PROMOTION_NOT_SAVE);
        }
    }

    public void updatePromotion(Long promotionId, SavePromotionReqDTO savePromotionReqDTO) {

        Promotion promotion = promotionRepository.findById(promotionId)
                .orElseThrow(() -> new CustomException(ErrorCode.PROMOTION_NOT_FOUND));

        modelMapper.map(savePromotionReqDTO, promotion);
    }

    public void deletePromotion(Long promotionId) {

        try {
            promotionRepository.deleteById(promotionId);
        } catch (Exception e) {
            throw new CustomException(ErrorCode.PROMOTION_NOT_DELETE);
        }
    }
}
