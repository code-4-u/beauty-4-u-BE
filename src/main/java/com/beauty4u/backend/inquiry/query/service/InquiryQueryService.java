package com.beauty4u.backend.inquiry.query.service;

import com.beauty4u.backend.inquiry.query.dto.InquiryListResDTO;
import com.beauty4u.backend.inquiry.query.mapper.InquiryQueryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InquiryQueryService {

    private final InquiryQueryMapper inquiryQueryMapper;

    @Transactional(readOnly = true)
    public List<InquiryListResDTO> findInquiryList(Long page, Long count) {

        long offset = (page - 1) * count;

        return inquiryQueryMapper.findInquiryList(offset, count);
    }
}
