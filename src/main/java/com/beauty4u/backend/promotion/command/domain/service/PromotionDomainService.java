package com.beauty4u.backend.promotion.command.domain.service;

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

        promotionRepository.save(promotion);
    }
}
