package com.beauty4u.backend.inquiry.command.application.service;

import com.beauty4u.backend.inquiry.command.application.dto.QnaReplyReqDTO;
import com.beauty4u.backend.inquiry.command.domain.service.InquiryReplyDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class InquiryReplyService {

    private final InquiryReplyDomainService inquiryReplyDomainService;

    @Transactional
    public void saveQnaReply(String loginUserCode, Long inquiryId, QnaReplyReqDTO qnaReplyReqDTO) {

        inquiryReplyDomainService.saveQnaReply(loginUserCode, inquiryId, qnaReplyReqDTO);
    }

    @Transactional
    public void updateQnaReply(Long inquiryId, QnaReplyReqDTO qnaReplyReqDTO) {

        inquiryReplyDomainService.updateQnaReply(inquiryId, qnaReplyReqDTO);
    }

    @Transactional
    public void deleteQnaReply(Long inquiryId) {

        inquiryReplyDomainService.deleteQnaReply(inquiryId);
    }
}
