package com.beauty4u.backend.inquiry.query.service;

import com.beauty4u.backend.common.exception.CustomException;
import com.beauty4u.backend.common.exception.ErrorCode;
import com.beauty4u.backend.inquiry.query.dto.InquiryDetailResDTO;
import com.beauty4u.backend.inquiry.query.dto.InquiryFilterReqDTO;
import com.beauty4u.backend.inquiry.query.dto.InquiryListDTO;
import com.beauty4u.backend.inquiry.query.dto.InquiryListResDTO;
import com.beauty4u.backend.inquiry.query.mapper.InquiryQueryMapper;
import com.beauty4u.backend.user.command.domain.aggregate.UserInfo;
import com.beauty4u.backend.user.command.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InquiryQueryService {

    private final InquiryQueryMapper inquiryQueryMapper;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public InquiryListResDTO findInquiryList(String loginUserCode, InquiryFilterReqDTO inquiryFilterReqDTO) {

        Long offset = (inquiryFilterReqDTO.getPage() - 1) * inquiryFilterReqDTO.getCount();

        InquiryListResDTO inquiryListResDTO = null;

        UserInfo userInfo = userRepository.findById(loginUserCode)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER));

        try {
            List<InquiryListDTO> inquiryListDTOS = inquiryQueryMapper.findInquiryList(
                    loginUserCode,
                    inquiryFilterReqDTO.getQnaTitle(),
                    inquiryFilterReqDTO.getPublishStatus(),
                    userInfo.getUserRole().getUserRoleName(),
                    inquiryFilterReqDTO.getSort(),
                    inquiryFilterReqDTO.getOrder(),
                    offset,
                    inquiryFilterReqDTO.getCount());

            inquiryListResDTO = new InquiryListResDTO();
            inquiryListResDTO.setQnaList(inquiryListDTOS);

            Long totalCount = inquiryQueryMapper.findInquiryListTotalCount(
                    inquiryFilterReqDTO.getQnaTitle(),
                    inquiryFilterReqDTO.getPublishStatus(),
                    userInfo.getUserRole().getUserRoleName(),
                    inquiryFilterReqDTO.getSort(),
                    inquiryFilterReqDTO.getOrder());

            inquiryListResDTO.setTotalCount(totalCount);
        } catch (Exception e) {
            throw new CustomException(ErrorCode.NOT_FOUND_INQUIRY_LIST);
        }


        return inquiryListResDTO;
    }

    @Transactional(readOnly = true)
    public InquiryDetailResDTO findInquiryDetail(Long inquiryId) {

        return inquiryQueryMapper.findInquiryDetail(inquiryId);
    }
}
