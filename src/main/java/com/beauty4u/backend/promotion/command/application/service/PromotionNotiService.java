package com.beauty4u.backend.promotion.command.application.service;

import com.beauty4u.backend.promotion.command.application.dto.SavePromotionNotiDTO;
import com.beauty4u.backend.promotion.command.application.dto.UpdatePromotionNotiDTO;
import com.beauty4u.backend.promotion.command.domain.service.PromotionNotiDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PromotionNotiService {

    private final PromotionNotiDomainService promotionNotiDomainService;

    @Transactional
    public void savePromotionNoti(SavePromotionNotiDTO savePromotionNotiDTO) {
        promotionNotiDomainService.savePromotionNoti(savePromotionNotiDTO);
    }

    @Transactional
    public void updatePromotionNoti(Long id, UpdatePromotionNotiDTO updatePromotionNotiDTO) {
        promotionNotiDomainService.updatePromotionNoti(id, updatePromotionNotiDTO);
    }

    @Transactional
    public void deletePromotionNoti(Long id) {
        promotionNotiDomainService.deletePromotionNoti(id);
    }
}
