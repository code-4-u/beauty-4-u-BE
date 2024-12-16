package com.beauty4u.backend.promotion.query.service;

import com.beauty4u.backend.common.exception.CustomException;
import com.beauty4u.backend.common.exception.ErrorCode;
import com.beauty4u.backend.promotion.query.dto.FindPromotionListReqDTO;
import com.beauty4u.backend.promotion.query.dto.PromotionDetailResDTO;
import com.beauty4u.backend.promotion.query.mapper.PromotionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PromotionQueryService {

    private final PromotionMapper promotionMapper;

    public PromotionDetailResDTO findPromotionDetail(Long promotionId) {

        PromotionDetailResDTO findPromotionDTO = null;
        try {
            findPromotionDTO = promotionMapper.findPromotionById(promotionId);
        } catch (Exception e) {
            throw new CustomException(ErrorCode.PROMOTION_FIND_DETAIL_FAIL);
        }

        return findPromotionDTO;
    }

    @Transactional(readOnly = true)
    public List<PromotionDetailResDTO> findPromotionList(FindPromotionListReqDTO findPromotionListReqDTO) {

        Long offset = (findPromotionListReqDTO.getPage() - 1) * findPromotionListReqDTO.getCount();

        System.out.println("offset:" + offset);
        System.out.println("count: " + findPromotionListReqDTO.getCount());

        LocalDateTime startDateTime = null;
        LocalDateTime endDateTime = null; // 종료일의 23:59:59

        System.out.println(findPromotionListReqDTO);

        if (findPromotionListReqDTO.getPromotionStartDate() != null) {
            startDateTime = findPromotionListReqDTO.getPromotionStartDate().atStartOfDay();
        }

        if (findPromotionListReqDTO.getPromotionEndDate() != null) {
            endDateTime = findPromotionListReqDTO.getPromotionEndDate().atTime(23, 59, 59);
        }

        List<PromotionDetailResDTO> promotionList = promotionMapper.findPromotionList(
                findPromotionListReqDTO.getPromotionTitle(),
                startDateTime,
                endDateTime,
                findPromotionListReqDTO.getPromotionStatus(),
                findPromotionListReqDTO.getSort(),
                findPromotionListReqDTO.getOrder(),
                offset,
                findPromotionListReqDTO.getCount()
        );

        System.out.println(promotionList.size());

        return promotionList;
    }
}
