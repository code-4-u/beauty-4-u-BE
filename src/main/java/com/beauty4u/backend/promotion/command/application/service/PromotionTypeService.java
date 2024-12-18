package com.beauty4u.backend.promotion.command.application.service;

import com.beauty4u.backend.promotion.command.application.dto.SavePromotionTypeDTO;
import com.beauty4u.backend.promotion.command.application.dto.UpdatePromotionTypeDTO;
import com.beauty4u.backend.promotion.command.domain.service.PromotionTypeDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PromotionTypeService {

    private final PromotionTypeDomainService promotionTypeDomainService;

    @Transactional
    public void savePromotionType(SavePromotionTypeDTO savePromotionTypeDTO) {

        promotionTypeDomainService.savePromotionType(savePromotionTypeDTO);
    }

    @Transactional
    public void updatePromotionType(Long id, UpdatePromotionTypeDTO updatePromotionTypeDTO) {

        promotionTypeDomainService.updatePromotionType(id, updatePromotionTypeDTO);
    }
}
