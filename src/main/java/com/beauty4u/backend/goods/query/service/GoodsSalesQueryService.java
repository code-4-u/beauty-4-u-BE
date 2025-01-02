package com.beauty4u.backend.goods.query.service;

import com.beauty4u.backend.common.exception.CustomException;
import com.beauty4u.backend.common.exception.ErrorCode;
import com.beauty4u.backend.goods.query.dto.*;
import com.beauty4u.backend.goods.query.mapper.GoodsSalesQueryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GoodsSalesQueryService {

    private final GoodsSalesQueryMapper goodsSalesQueryMapper;

    @Transactional(readOnly = true)
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

            if (currentYearMontlySales == null) {
                currentYearMontlySales = 0L;
            }

            // 전 연도의 매출액 조회
            Long lastYearMonthlySales = goodsSalesQueryMapper.findGoodsMonthlySales(
                    goodsCode,
                    month,
                    lastYear
            );

            if (lastYearMonthlySales == null) {
                lastYearMonthlySales = 0L;
            }

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

    @Transactional(readOnly = true)
    public List<GoodsSalesMonthlyListResDTO> findSalesGoodsMonthlyList(String goodsCode, GoodsSalesMonthlyListFilterDTO goodsSalesMonthlyListFilterDTO) {

        List<GoodsSalesMonthlyListResDTO> goodsSalesMonthlyListResDTOS = new ArrayList<>();

        try {
            // 각 월에 대한 매출액 조회
            for (int month = 1; month <= 12; month++) {
                Long goodsMonthlySales = goodsSalesQueryMapper.findGoodsMonthlySales(
                        goodsCode,
                        month,
                        goodsSalesMonthlyListFilterDTO.getYear()
                );

                // 각 월에 대한 dto 생성
                GoodsSalesMonthlyListResDTO goodsSalesMonthlyListResDTO
                        = new GoodsSalesMonthlyListResDTO(month, goodsMonthlySales);

                // 만들어진 dto 추가
                goodsSalesMonthlyListResDTOS.add(goodsSalesMonthlyListResDTO);
            }
        } catch (Exception e) {
            throw new CustomException(ErrorCode.GOODS_SALES_MONTHLY_LIST_FIND_FAIL);
        }

        return goodsSalesMonthlyListResDTOS;
    }

    @Transactional(readOnly = true)
    public List<GoodsSalesAgeListResDTO> findSalesGoodsAge(
            String goodsCode, GoodsSalesAgeListFilterDTO goodsSalesAgeListFilterDTO) {

        int[] ages = {10, 20, 30, 40, 50, 60};

        int month = goodsSalesAgeListFilterDTO.getMonth();

        int beforeYear = goodsSalesAgeListFilterDTO.getYear() - 1;
        int afterYear = goodsSalesAgeListFilterDTO.getYear();

        List<GoodsSalesAgeListResDTO> goodsSalesAgeListResDTOS  = new ArrayList<>();

        GoodsSalesAgeListResDTO beforeSales = new GoodsSalesAgeListResDTO();
        GoodsSalesAgeListResDTO afterSales = new GoodsSalesAgeListResDTO();

        beforeSales.setMonthSalesList(new ArrayList<>());
        afterSales.setMonthSalesList(new ArrayList<>());

        for (int age : ages) {

            int endAge = age + 10;

            if (age == 60) endAge = 100;

            Long beforeSalesGoodsAge = goodsSalesQueryMapper.findSalesGoodsAge(
                    goodsCode,
                    beforeYear,
                    month,
                    age,
                    endAge
            );

            if (beforeSalesGoodsAge == null) {
                beforeSalesGoodsAge = 0L;
            }

            MonthSalesDTO beforeMonthSalesDTO = new MonthSalesDTO();
            beforeMonthSalesDTO.setAgeUnit(age);
            beforeMonthSalesDTO.setSales(beforeSalesGoodsAge);

            beforeSales.getMonthSalesList().add(beforeMonthSalesDTO);
            beforeSales.setYear(beforeYear);

            Long afterSalesGoodsAge = goodsSalesQueryMapper.findSalesGoodsAge(
                    goodsCode,
                    afterYear,
                    month,
                    age,
                    endAge
            );

            if (afterSalesGoodsAge == null) {
                afterSalesGoodsAge = 0L;
            }

            MonthSalesDTO afterMonthSalesDTO = new MonthSalesDTO();
            afterMonthSalesDTO.setAgeUnit(age);
            afterMonthSalesDTO.setSales(afterSalesGoodsAge);

            afterSales.getMonthSalesList().add(afterMonthSalesDTO);
            afterSales.setYear(afterYear);
        }

        goodsSalesAgeListResDTOS.add(beforeSales);
        goodsSalesAgeListResDTOS.add(afterSales);

        return goodsSalesAgeListResDTOS;
    }
}
