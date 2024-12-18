package com.beauty4u.backend.promotion.command.domain.service;

import com.beauty4u.backend.common.exception.CustomException;
import com.beauty4u.backend.common.exception.ErrorCode;
import com.beauty4u.backend.promotion.command.application.dto.SavePromotionTypeDTO;
import com.beauty4u.backend.promotion.command.application.dto.UpdatePromotionTypeDTO;
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

    public void updatePromotionType(Long id, UpdatePromotionTypeDTO updatePromotionTypeDTO) {

        PromotionType promotionType = promotionTypeRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_PROMOTIONTYPE));

        modelMapper.map(updatePromotionTypeDTO, promotionType);
    }

    public void deletePromotionTypeById(Long promotionId) {

        promotionTypeRepository.deleteById(promotionId);
    }
}
