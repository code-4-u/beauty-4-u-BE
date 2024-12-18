package com.beauty4u.backend.promotion.command.application.service;

import com.beauty4u.backend.promotion.command.application.dto.SavePromotionTypeDTO;
import com.beauty4u.backend.promotion.command.domain.service.PromotionTypeDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PromotionTypeService {

    private final PromotionTypeDomainService promotionTypeDomainService;

    public void savePromotionType(SavePromotionTypeDTO savePromotionTypeDTO) {

        promotionTypeDomainService.savePromotionType(savePromotionTypeDTO);
    }
}
