package com.beauty4u.backend.inquiry.query.service;

import com.beauty4u.backend.common.exception.CustomException;
import com.beauty4u.backend.common.exception.ErrorCode;
import com.beauty4u.backend.inquiry.query.dto.InquiryReplyResDTO;
import com.beauty4u.backend.inquiry.query.mapper.InquiryReplyQueryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class InquiryReplyQueryService {

    private final InquiryReplyQueryMapper inquiryReplyMapper;

    @Transactional(readOnly = true)
    public InquiryReplyResDTO findInquiryReplyDetail(Long inquiryId) {

        try {
            return inquiryReplyMapper.findInquiryReplyDetail(inquiryId);
        } catch (Exception e) {
            throw new CustomException(ErrorCode.NOT_FOUND_INQUIRY_REPLY);
        }
    }
}
