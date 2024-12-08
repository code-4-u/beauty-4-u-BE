package com.beauty4u.backend.inquiry.command.application.service;

import com.beauty4u.backend.inquiry.command.application.dto.CreateQnaReqDTO;
import com.beauty4u.backend.inquiry.command.domain.service.InquiryDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class InquiryCommandService {

    private final InquiryDomainService inquiryDomainService;

    @Transactional
    public void saveQna(String loginUserCode, CreateQnaReqDTO createQnaReqDTO) {

        inquiryDomainService.saveQna(loginUserCode, createQnaReqDTO);
    }
}
