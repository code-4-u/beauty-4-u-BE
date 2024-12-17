package com.beauty4u.backend.inquiry.query.service;

import com.beauty4u.backend.inquiry.query.dto.FaqDetailResDTO;
import com.beauty4u.backend.inquiry.query.dto.FaqFilterReqDTO;
import com.beauty4u.backend.inquiry.query.dto.FaqListDTO;
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
    public FaqListResDTO findFaqList(FaqFilterReqDTO faqFilterReqDTO) {

        Long offset = (faqFilterReqDTO.getPage() - 1) * faqFilterReqDTO.getCount();

        List<FaqListDTO> faqList = faqQueryMapper.findFaqList(
                faqFilterReqDTO.getFaqTitle(),
                faqFilterReqDTO.getPublishStatus(),
                faqFilterReqDTO.getSort(),
                faqFilterReqDTO.getOrder(),
                offset,
                faqFilterReqDTO.getCount());

        FaqListResDTO faqListResDTO = new FaqListResDTO();
        faqListResDTO.setFaqList(faqList);

        Long totalCount = faqQueryMapper.findFaqListTotalCount(
                faqFilterReqDTO.getFaqTitle());

        faqListResDTO.setTotalCount(totalCount);

        return faqListResDTO;
    }

    @Transactional(readOnly = true)
    public FaqDetailResDTO findFaqDetail(Long faqId) {

        return faqQueryMapper.findFaqDetail(faqId);
    }
}
