package com.beauty4u.backend.promotion.command.domain.service;

import com.beauty4u.backend.promotion.command.application.dto.SavePromotionTypeDTO;
import com.beauty4u.backend.promotion.command.domain.aggregate.PromotionType;
import com.beauty4u.backend.promotion.command.domain.repository.PromotionTypeRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PromotionTypeDomainService {

    private final PromotionTypeRepository promotionTypeRepository;
    private final ModelMapper modelMapper;

    public void savePromotionType(SavePromotionTypeDTO savePromotionTypeDTO) {

        PromotionType promotionType = modelMapper.map(savePromotionTypeDTO, PromotionType.class);

        promotionTypeRepository.save(promotionType);
    }
}
