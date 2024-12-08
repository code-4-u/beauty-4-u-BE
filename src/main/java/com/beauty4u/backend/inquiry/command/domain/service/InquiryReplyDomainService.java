package com.beauty4u.backend.inquiry.command.domain.service;

import com.beauty4u.backend.common.exception.CustomException;
import com.beauty4u.backend.common.exception.ErrorCode;
import com.beauty4u.backend.inquiry.command.application.dto.QnaReplyReqDTO;
import com.beauty4u.backend.inquiry.command.domain.aggregate.Inquiry;
import com.beauty4u.backend.inquiry.command.domain.aggregate.InquiryReply;
import com.beauty4u.backend.inquiry.command.domain.repository.InquiryReplyRepository;
import com.beauty4u.backend.inquiry.command.domain.repository.InquiryRepository;
import com.beauty4u.backend.user.command.domain.aggregate.UserInfo;
import com.beauty4u.backend.user.command.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InquiryReplyDomainService {

    private final InquiryReplyRepository inquiryReplyRepository;
    private final InquiryRepository inquiryRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public void saveQnaReply(String loginUserCode, Long inquiryId, QnaReplyReqDTO qnaReplyReqDTO) {

        InquiryReply inquiryReply = modelMapper.map(qnaReplyReqDTO, InquiryReply.class);

        Inquiry inquiry = inquiryRepository.findById(inquiryId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_INQUIRY));

        UserInfo user = userRepository.findByUserCode(loginUserCode)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER));

        inquiryReply.saveReply(inquiry, user);

        inquiry.saveReply();

        try {
            inquiryReplyRepository.save(inquiryReply);
        } catch (Exception e) {
            throw new CustomException(ErrorCode.NOT_SAVED_INQUIRY_REPLY);
        }
    }
}
