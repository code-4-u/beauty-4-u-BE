package com.beauty4u.backend.inquiry.query.service;

import com.beauty4u.backend.inquiry.query.dto.InquiryFilterReqDTO;
import com.beauty4u.backend.inquiry.query.dto.InquiryListDTO;
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
    public InquiryListResDTO findInquiryList(InquiryFilterReqDTO inquiryFilterReqDTO) {

        Long offset = (inquiryFilterReqDTO.getPage() - 1) * inquiryFilterReqDTO.getCount();

        List<InquiryListDTO> inquiryListDTOS = inquiryQueryMapper.findInquiryList(
                inquiryFilterReqDTO.getQnaTitle(),
                inquiryFilterReqDTO.getPublishStatus(),
                inquiryFilterReqDTO.getSort(),
                inquiryFilterReqDTO.getOrder(),
                offset,
                inquiryFilterReqDTO.getCount());

        InquiryListResDTO inquiryListResDTO = new InquiryListResDTO();
        inquiryListResDTO.setQnaList(inquiryListDTOS);

        Long totalCount = inquiryQueryMapper.findInquiryListTotalCount(
                inquiryFilterReqDTO.getQnaTitle(),
                inquiryFilterReqDTO.getPublishStatus(),
                inquiryFilterReqDTO.getSort(),
                inquiryFilterReqDTO.getOrder());

        inquiryListResDTO.setTotalCount(totalCount);

        return inquiryListResDTO;
    }
}
