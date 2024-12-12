package com.beauty4u.backend.inquiry.query.service;

import com.beauty4u.backend.inquiry.query.dto.FaqDetailResDTO;
import com.beauty4u.backend.inquiry.query.dto.FaqListResDTO;
import com.beauty4u.backend.inquiry.query.mapper.FaqQueryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FaqQueryService {

    private final FaqQueryMapper faqQueryMapper;

    @Transactional(readOnly = true)
    public List<FaqListResDTO> findFaqList(Long page, Long count) {

        long offset = (page - 1) * count;

        return faqQueryMapper.findFaqList(offset, count);
    }

    @Transactional(readOnly = true)
    public FaqDetailResDTO findFaqDetail(Long faqId) {

        return faqQueryMapper.findFaqDetail(faqId);
    }
}
