package com.beauty4u.backend.promotion.query.service;

import com.beauty4u.backend.common.exception.CustomException;
import com.beauty4u.backend.common.exception.ErrorCode;
import com.beauty4u.backend.promotion.query.dto.*;
import com.beauty4u.backend.promotion.query.mapper.PromotionGoodsQueryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PromotionGoodsQueryService {

    private final PromotionGoodsQueryMapper promotionGoodsQueryMapper;

    public List<FindPromotionGoodsListResDTO> findPromotionGoodsList(
            Long promotionId,
            FindPromotionGoodsListFilterDTO findPromotionGoodsListFilterDTO
    ) {

        Long offset = (findPromotionGoodsListFilterDTO.getPage() - 1) * findPromotionGoodsListFilterDTO.getCount();

        List<FindPromotionGoodsListResDTO> promotionGoodsList = null;

        try {
            promotionGoodsList = promotionGoodsQueryMapper.findPromotionGoodsList(
                    promotionId,
                    findPromotionGoodsListFilterDTO.getGoodsName(),
                    findPromotionGoodsListFilterDTO.getSort(),
                    findPromotionGoodsListFilterDTO.getOrder(),
                    offset,
                    findPromotionGoodsListFilterDTO.getCount()
            );
        } catch (Exception e) {
            throw new CustomException(ErrorCode.PROMOTION_GOODS_LIST_NOT_FOUND);
        }

        return promotionGoodsList;
    }

    public List<FindPromotionGoodsCommonInfoResDTO> findPromotionGoodsCommonInfoList(
            String goodsCode,
            FindPromotionGoodsCommonInfoFilterDTO findPromotionGoodsCommonInfoFilterDTO
    ) {

        Long offset = (findPromotionGoodsCommonInfoFilterDTO.getPage() - 1) * findPromotionGoodsCommonInfoFilterDTO.getCount();

        // 반환할 프로모션 기본 정보 리스트
        List<FindPromotionGoodsCommonInfoResDTO> findPromotionGoodsCommonInfoResDTOs = new ArrayList<>();

        try {

            // 프로모션 리스트 기본 정보를 가져올 메서드
            List<FindPromotionGoodsSalesDTO> findPromotionGoodsSalesDTOS = promotionGoodsQueryMapper.findPromotionGoodsCommonInfoList(
                    goodsCode,
                    findPromotionGoodsCommonInfoFilterDTO.getYear(),
                    findPromotionGoodsCommonInfoFilterDTO.getMonth(),
                    findPromotionGoodsCommonInfoFilterDTO.getPromotionTitle(),
                    findPromotionGoodsCommonInfoFilterDTO.getSort(),
                    findPromotionGoodsCommonInfoFilterDTO.getOrder(),
                    offset,
                    findPromotionGoodsCommonInfoFilterDTO.getCount()
            );

            // 가져온 프로모션들로 각 프로모션에 대해서 기간에 대한 매출액, 전년 평균 매출액, 평균 매출액 대비 매출액 증감율 계산
            for (FindPromotionGoodsSalesDTO findPromotionGoodsSalesDTO : findPromotionGoodsSalesDTOS) {

                FindPromotionGoodsCommonInfoResDTO findPromotionGoodsCommonInfoResDTO
                        = new FindPromotionGoodsCommonInfoResDTO();

                LocalDateTime promotionStartDate = findPromotionGoodsSalesDTO.getPromotionStartDate();
                LocalDateTime promotionEndDate = findPromotionGoodsSalesDTO.getPromotionEndDate();

                Long sales = promotionGoodsQueryMapper.findPromotionGoodsSales(
                        promotionStartDate,
                        promotionEndDate
                );

                LocalDateTime oneYearAgo = promotionStartDate.minusYears(1);

                Long totalSales = promotionGoodsQueryMapper.findPromotionGoodsAvgSales(
                        oneYearAgo,
                        promotionStartDate
                );

                long days = ChronoUnit.DAYS.between(promotionStartDate, promotionEndDate);

                long avgSales = totalSales / days; // 평균 매출액 계산

                double percent = calculateIncreaseRate(sales, avgSales);

                findPromotionGoodsCommonInfoResDTO.modify(
                        findPromotionGoodsSalesDTO,
                        sales,
                        avgSales,
                        percent
                );

                findPromotionGoodsCommonInfoResDTOs.add(findPromotionGoodsCommonInfoResDTO);
            }
        } catch (Exception e) {
            throw new CustomException(ErrorCode.GOODS_PROMOTION_COMMON_INFO_LIST_FIND_FAIL);
        }

        return findPromotionGoodsCommonInfoResDTOs;
    }

    // 증감율 계산 메서드
    public double calculateIncreaseRate(long sales, long avgSales) {
        if (avgSales == 0) {
            return 0; // 또는 예외 처리
        }

        double increaseRate = (double) (sales - avgSales) / avgSales * 100;
        return Math.round(increaseRate * 10) / 10.0; // 소수점 첫째 자리까지 반올림
    }
}
