package com.beauty4u.backend.inquiry.command.application.service;

import com.beauty4u.backend.inquiry.command.application.dto.QnaReqDTO;
import com.beauty4u.backend.inquiry.command.application.dto.UpdateQnaViewcount;
import com.beauty4u.backend.inquiry.command.domain.service.InquiryDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class InquiryService {

    private final InquiryDomainService inquiryDomainService;

    @Transactional
    public Long saveQna(String loginUserCode, QnaReqDTO qnaReqDTO) {

        return inquiryDomainService.saveQna(loginUserCode, qnaReqDTO);
    }

    @Transactional
    public void updateQna(Long inquiryId, QnaReqDTO qnaReqDTO) {

        inquiryDomainService.updateQna(inquiryId, qnaReqDTO);
    }

    @Transactional
    public void deleteQna(Long inquiryId) {

        inquiryDomainService.deleteQna(inquiryId);
    }

    @Transactional
    public void updateFaqViewcount(Long inquiryId, UpdateQnaViewcount updateQnaViewcount) {

        inquiryDomainService.updateFaqViewcount(inquiryId, updateQnaViewcount);
    }
}
