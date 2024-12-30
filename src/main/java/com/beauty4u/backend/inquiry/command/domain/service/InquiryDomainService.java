package com.beauty4u.backend.inquiry.command.domain.service;

import com.beauty4u.backend.common.exception.CustomException;
import com.beauty4u.backend.common.exception.ErrorCode;
import com.beauty4u.backend.inquiry.command.application.dto.InquiryDTO;
import com.beauty4u.backend.inquiry.command.application.dto.QnaReqDTO;
import com.beauty4u.backend.inquiry.command.application.dto.UpdateQnaViewcount;
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

    public Long saveQna(String loginUserCode, QnaReqDTO qnaReqDTO) {

        Inquiry inquiry = modelMapper.map(qnaReqDTO, Inquiry.class);

        UserInfo user = userRepository.findByUserCode(loginUserCode)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER));

        inquiry.modifyUser(user);

        try {
            inquiryRepository.save(inquiry);
        } catch (Exception e) {
            throw new CustomException(ErrorCode.NOT_SAVED_INQUIRY);
        }

        return inquiry.getId();
    }

    public void updateQna(String loginUserCode, Long inquiryId, QnaReqDTO qnaReqDTO) {

        String title = qnaReqDTO.getInquiryTitle();
        String content = qnaReqDTO.getInquiryContent();
        String secretYn = qnaReqDTO.getInquirySecretYn();

        Inquiry inquiry = inquiryRepository.findById(inquiryId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_INQUIRY));

        if (inquiry.getUserCode().equals(loginUserCode)) {

            throw new CustomException(ErrorCode.NOT_SAME_USER);
        }

        inquiry.modifyInquiry(title, content, secretYn);
    }

    public void deleteQna(Long inquiryId) {

        Inquiry inquiry = inquiryRepository.findById(inquiryId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_INQUIRY));

        inquiryRepository.delete(inquiry);
    }

    public InquiryDTO findInquiry(Long inquiryId) {

        Inquiry findInquiry = inquiryRepository.findById(inquiryId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_INQUIRY));

        return modelMapper.map(findInquiry, InquiryDTO.class);
    }

    public void updateFaqViewcount(Long inquiryId, UpdateQnaViewcount updateQnaViewcount) {

        Inquiry inquiry = inquiryRepository.findById(inquiryId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_INQUIRY));

        inquiry.modifyViewcount(updateQnaViewcount.getQnaViewcount());
    }
}
