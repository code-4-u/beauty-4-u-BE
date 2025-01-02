package com.beauty4u.backend.promotion.command.domain.service;

import com.beauty4u.backend.common.exception.CustomException;
import com.beauty4u.backend.common.exception.ErrorCode;
import com.beauty4u.backend.promotion.command.application.dto.SavePromotionReqDTO;
import com.beauty4u.backend.promotion.command.domain.aggregate.Promotion;
import com.beauty4u.backend.promotion.command.domain.aggregate.PromotionType;
import com.beauty4u.backend.promotion.command.domain.repository.PromotionRepository;
import com.beauty4u.backend.promotion.command.domain.repository.PromotionTypeRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PromotionDomainService {

    private final PromotionRepository promotionRepository;
    private final PromotionTypeRepository promotionTypeRepository;
    private final ModelMapper modelMapper;

    public Long savePromotion(SavePromotionReqDTO savePromotionReqDTO) {

        PromotionType promotionType = promotionTypeRepository.findById(savePromotionReqDTO.getPromotionType())
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_PROMOTIONTYPE));

        int promotionYear = savePromotionReqDTO.getPromotionStartDate().getYear();

        // 같은 해에 프로모션이 2개 이상 존재하는지 확인
        Boolean result = promotionRepository.existsPromotionInSameYear(
                savePromotionReqDTO.getPromotionType(), promotionYear);

        // 만약 프로모션이 이미 존재하면
        if (result != null && result) {
            throw new CustomException(ErrorCode.PROMOTION_EXISTS_IN_SAME_YEAR);
        }

        Promotion promotion = modelMapper.map(savePromotionReqDTO, Promotion.class);

        promotion.modifyPromtionType(promotionType);

        try {
            promotionRepository.save(promotion);
        } catch (Exception e) {
            throw new CustomException(ErrorCode.PROMOTION_NOT_SAVE);
        }

        return promotion.getId();
    }

    public void updatePromotion(Long promotionId, SavePromotionReqDTO savePromotionReqDTO) {

        Promotion promotion = promotionRepository.findById(promotionId)
                .orElseThrow(() -> new CustomException(ErrorCode.PROMOTION_NOT_FOUND));

        int currentPromotionYear = promotion.getPromotionStartDate().getYear();
        int updatePromotionYear = savePromotionReqDTO.getPromotionStartDate().getYear();

        // 수정하려는 연도가 기존 연도와 같지 않으면
        if (currentPromotionYear != updatePromotionYear) {

            // 같은 해에 프로모션이 2개 이상 존재하는지 확인
            Boolean result = promotionRepository.existsPromotionInSameYear(
                    savePromotionReqDTO.getPromotionType(), updatePromotionYear);

            // 만약 프로모션이 이미 존재하면
            if (result != null && result) {
                throw new CustomException(ErrorCode.PROMOTION_EXISTS_IN_SAME_YEAR);
            }
        }

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
