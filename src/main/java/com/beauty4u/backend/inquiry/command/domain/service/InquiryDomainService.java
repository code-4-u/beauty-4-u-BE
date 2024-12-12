package com.beauty4u.backend.inquiry.command.domain.service;

import com.beauty4u.backend.common.exception.CustomException;
import com.beauty4u.backend.common.exception.ErrorCode;
import com.beauty4u.backend.inquiry.command.application.dto.QnaReqDTO;
import com.beauty4u.backend.inquiry.command.domain.aggregate.Inquiry;
import com.beauty4u.backend.inquiry.command.domain.repository.InquiryRepository;
import com.beauty4u.backend.user.command.domain.aggregate.UserInfo;
import com.beauty4u.backend.user.command.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InquiryDomainService {

    private final InquiryRepository inquiryRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public void saveQna(String loginUserCode, QnaReqDTO qnaReqDTO) {

        Inquiry inquiry = modelMapper.map(qnaReqDTO, Inquiry.class);

        UserInfo user = userRepository.findByUserCode(loginUserCode)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER));

        inquiry.modifyUser(user);

        try {
            inquiryRepository.save(inquiry);
        } catch (Exception e) {
            throw new CustomException(ErrorCode.NOT_SAVED_INQUIRY);
        }
    }

    public void updateQna(Long inquiryId, QnaReqDTO qnaReqDTO) {

        String title = qnaReqDTO.getInquiryTitle();
        String content = qnaReqDTO.getInquiryContent();
        String secretYn = qnaReqDTO.getInquirySecretYn();

        Inquiry inquiry = inquiryRepository.findById(inquiryId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_INQUIRY));

        inquiry.modifyInquiry(title, content, secretYn);
    }

    public void deleteQna(Long inquiryId) {

        Inquiry inquiry = inquiryRepository.findById(inquiryId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_INQUIRY));

        inquiryRepository.delete(inquiry);
    }
}
