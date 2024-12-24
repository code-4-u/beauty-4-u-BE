package com.beauty4u.backend.promotion.command.domain.service;

import com.beauty4u.backend.common.exception.CustomException;
import com.beauty4u.backend.common.exception.ErrorCode;
import com.beauty4u.backend.promotion.command.application.dto.SavePromotionNotiDTO;
import com.beauty4u.backend.promotion.command.application.dto.UpdatePromotionNotiDTO;
import com.beauty4u.backend.promotion.command.domain.aggregate.PromotionNoti;
import com.beauty4u.backend.promotion.command.domain.repository.PromotionNotiRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PromotionNotiDomainService {

    private final PromotionNotiRepository promotionNotiRepository;
    private final ModelMapper modelMapper;

    public void savePromotionNoti(SavePromotionNotiDTO savePromotionNotiDTO) {
        PromotionNoti promotionNoti = modelMapper.map(savePromotionNotiDTO, PromotionNoti.class);
        promotionNotiRepository.save(promotionNoti);
    }

    public void updatePromotionNoti(Long id, UpdatePromotionNotiDTO updatePromotionNotiDTO) {
        PromotionNoti promotionNoti = promotionNotiRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.PROMOTION_NOT_FOUND));
        modelMapper.map(updatePromotionNotiDTO, promotionNoti);
    }

    public void deletePromotionNoti(Long id) {
        promotionNotiRepository.deleteById(id);
    }
}
