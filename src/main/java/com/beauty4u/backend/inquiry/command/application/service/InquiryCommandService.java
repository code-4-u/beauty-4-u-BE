package com.beauty4u.backend.inquiry.command.application.service;

import com.beauty4u.backend.inquiry.command.application.dto.QnaReqDTO;
import com.beauty4u.backend.inquiry.command.domain.service.InquiryDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class InquiryCommandService {

    private final InquiryDomainService inquiryDomainService;

    @Transactional
    public void saveQna(String loginUserCode, QnaReqDTO qnaReqDTO) {

        inquiryDomainService.saveQna(loginUserCode, qnaReqDTO);
    }

    @Transactional
    public void updateQna(Long inquiryId, QnaReqDTO qnaReqDTO) {

        inquiryDomainService.updateQna(inquiryId, qnaReqDTO);
    }
}
