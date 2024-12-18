package com.beauty4u.backend.goods.query.service;

import com.beauty4u.backend.common.exception.CustomException;
import com.beauty4u.backend.common.exception.ErrorCode;
import com.beauty4u.backend.goods.query.dto.GoodsSalesFilterDTO;
import com.beauty4u.backend.goods.query.dto.GoodsSalesResDTO;
import com.beauty4u.backend.goods.query.mapper.GoodsSalesQueryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GoodsSalesQueryService {

    private final GoodsSalesQueryMapper goodsSalesQueryMapper;

    public GoodsSalesResDTO findSalesGoods(String goodsCode, GoodsSalesFilterDTO goodsSalesFilterDTO) {

        Integer month = goodsSalesFilterDTO.getMonth();
        Integer year = goodsSalesFilterDTO.getYear();
        GoodsSalesResDTO goodsSalesResDTO = null;

        try {
            // 기준 연도의 매출액 조회
            Long currentYearMontlySales = goodsSalesQueryMapper.findGoodsMonthlySales(
                    goodsCode,
                    month,
                    year
            );

            Integer lastYear = year - 1; // 작년

            // 전 연도의 매출액 조회
            Long lastYearMonthlySales = goodsSalesQueryMapper.findGoodsMonthlySales(
                    goodsCode,
                    month,
                    lastYear
            );

            // 증감율 계산
            double percent = calculateIncreaseRate(currentYearMontlySales, lastYearMonthlySales);

            goodsSalesResDTO = new GoodsSalesResDTO(
                    currentYearMontlySales,
                    lastYearMonthlySales,
                    percent
            );

        } catch (Exception e) {
            throw new CustomException(ErrorCode.GOODS_SALES_COMPARE_FIND_FAIL);
        }

        return goodsSalesResDTO;
    }

    // 증감율 계산 메서드
    public double calculateIncreaseRate(long currentSales, long lastSales) {
        if (lastSales == 0) {
            return 0; // 또는 예외 처리
        }

        double increaseRate = (double) (currentSales - lastSales) / lastSales * 100;
        return Math.round(increaseRate * 10) / 10.0; // 소수점 첫째 자리까지 반올림
    }
}
