package com.beauty4u.backend.inquiry.command.application.service;

import com.beauty4u.backend.inquiry.command.application.dto.InquiryDTO;
import com.beauty4u.backend.inquiry.command.application.dto.QnaReplyReqDTO;
import com.beauty4u.backend.inquiry.command.domain.service.InquiryDomainService;
import com.beauty4u.backend.inquiry.command.domain.service.InquiryReplyDomainService;
import com.beauty4u.backend.noti.command.domain.aggregate.NotiType;
import com.beauty4u.backend.noti.command.domain.service.NotiDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InquiryReplyService {

    private final InquiryReplyDomainService inquiryReplyDomainService;
    private final NotiDomainService notiDomainService;
    private final InquiryDomainService inquiryDomainService;

    @Transactional
    public void saveQnaReply(String loginUserCode, Long inquiryId, QnaReplyReqDTO qnaReplyReqDTO) {

        inquiryReplyDomainService.saveQnaReply(loginUserCode, inquiryId, qnaReplyReqDTO);

        InquiryDTO inquiryDTO = inquiryDomainService.findInquiry(inquiryId);

        List<String> receiverCodes = new ArrayList<>();
        receiverCodes.add(inquiryDTO.getUserCode().getUserCode());

        NotiType notiType = NotiType.INQUIRY;

        notiDomainService.send(loginUserCode, receiverCodes, notiType, notiType.getMessage(), qnaReplyReqDTO.getUrl());
    }

    @Transactional
    public void updateQnaReply(Long inquiryReplyId, QnaReplyReqDTO qnaReplyReqDTO) {

        inquiryReplyDomainService.updateQnaReply(inquiryReplyId, qnaReplyReqDTO);
    }

    @Transactional
    public void deleteQnaReply(Long inquiryReplyId) {

        inquiryReplyDomainService.deleteQnaReply(inquiryReplyId);
    }
}
