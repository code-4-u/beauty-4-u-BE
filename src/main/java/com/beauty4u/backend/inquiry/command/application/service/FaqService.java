package com.beauty4u.backend.inquiry.command.application.service;

import com.beauty4u.backend.inquiry.command.application.dto.FaqReqDTO;
import com.beauty4u.backend.inquiry.command.domain.service.FaqDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FaqService {

    private final FaqDomainService faqDomainService;

    @Transactional
    public void saveFaq(String loginUserCode, FaqReqDTO faqReqDTO) {

        faqDomainService.saveFaq(loginUserCode, faqReqDTO);
    }

    @Transactional
    public void updateFaq(Long faqId, FaqReqDTO faqReqDTO) {

        faqDomainService.updateFaq(faqId, faqReqDTO);
    }
}
