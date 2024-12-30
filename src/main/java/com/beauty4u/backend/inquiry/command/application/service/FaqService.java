package com.beauty4u.backend.inquiry.command.application.service;

import com.beauty4u.backend.common.exception.CustomException;
import com.beauty4u.backend.common.exception.ErrorCode;
import com.beauty4u.backend.inquiry.command.application.dto.FaqReqDTO;
import com.beauty4u.backend.inquiry.command.application.dto.UpdateFaqViewcount;
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

        try {
            faqDomainService.updateFaq(faqId, faqReqDTO);
        } catch (CustomException e) {
            throw e;
        } catch (Exception e) {
            throw new CustomException(ErrorCode.NOT_UPDATE_FAQ);
        }
    }

    @Transactional
    public void deleteFaq(Long faqId) {

        faqDomainService.deleteFaq(faqId);
    }

    @Transactional
    public void updateFaqViewcount(Long faqId, UpdateFaqViewcount updateFaqViewcount) {

        faqDomainService.updateFaqViewcount(faqId, updateFaqViewcount);
    }
}
