package com.beauty4u.backend.promotion.command.application.service;

import com.beauty4u.backend.promotion.command.application.dto.PromotionEmailResult;
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
    public PromotionEmailResult savePromotionNotiFakeSMTP(Long promotionId, Long analysisId) {
        return promotionNotiDomainService.savePromotionNotiFakeSMTP(promotionId, analysisId);
    }

    @Transactional
    public PromotionEmailResult sendPromotionNotiGmail(Long promotionId, Long analysisId) {
        return promotionNotiDomainService.sendPromotionNotiMail(promotionId, analysisId);
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
