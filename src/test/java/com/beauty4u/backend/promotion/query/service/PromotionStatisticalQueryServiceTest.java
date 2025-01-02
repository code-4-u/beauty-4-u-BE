package com.beauty4u.backend.promotion.query.service;

import com.beauty4u.backend.promotion.query.dto.FindPromotionByComparisonSalesResDTO;
import com.beauty4u.backend.promotion.query.dto.FindPromotionByTypeResDTO;
import com.beauty4u.backend.promotion.query.dto.FindPromotionByYearSalesResDTO;
import com.beauty4u.backend.promotion.query.mapper.PromotionStatisticalMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PromotionStatisticalQueryServiceTest {

    @Mock
    private PromotionStatisticalMapper promotionStatisticalMapper;

    @InjectMocks
    private PromotionStatisticalQueryService promotionStatisticalQueryService;

    @Test
    @DisplayName("프로모션 타입별 조회 ASC 조회")
    void findTypeByPromotion() {
        List<FindPromotionByTypeResDTO> mockData =  Arrays.asList(
                new FindPromotionByTypeResDTO(1,1,"아항 프로모션1"),
                new FindPromotionByTypeResDTO(2,1,"아항 프로모션2"),
                new FindPromotionByTypeResDTO(3,1,"아항 프로모션3")
        );

        when(promotionStatisticalMapper.findTypeByPromotion()).thenReturn(mockData);

        List<FindPromotionByTypeResDTO> result = promotionStatisticalQueryService.findTypeByPromotion();
        assertEquals(mockData, result);
    }

    @Test
    @DisplayName("프로모션 종류에 따른 년도별 그래프 조회")
    void findPromotionByYearSales() {
        List<FindPromotionByYearSalesResDTO> mockData =  Arrays.asList(
                new FindPromotionByYearSalesResDTO("아항 프로모션1", 1, 2123123),
                new FindPromotionByYearSalesResDTO("아항 프로모션2", 2, 123123123),
                new FindPromotionByYearSalesResDTO("아항 프로모션3", 3, 123123)
        );

        List<Integer> ids = Arrays.asList(1, 2, 3);

        when(promotionStatisticalMapper.findPromotionByYearSales(ids)).thenReturn(mockData);

        List<FindPromotionByYearSalesResDTO> result = promotionStatisticalQueryService.findPromotionByYearSales(ids);
        assertEquals(mockData, result);
    }

    @Test
    @DisplayName("프로모션 기간 상품 매출 랭킹 비교")
    void findPromotionByComparisonSales() {
        List<FindPromotionByComparisonSalesResDTO> mockData =  Arrays.asList(
                new FindPromotionByComparisonSalesResDTO("이름1", 12, 3, 3, 1, "5 순위 증가", "23% 증가"),
                new FindPromotionByComparisonSalesResDTO("이름1", 12, 3, 3, 1, "5 순위 증가", "23% 증가"),
                new FindPromotionByComparisonSalesResDTO("이름1", 12, 3, 3, 1, "5 순위 증가", "23% 증가")
        );

        when(promotionStatisticalMapper.findPromotionByComparisonSales(1,2)).thenReturn(mockData);

        List<FindPromotionByComparisonSalesResDTO> result = promotionStatisticalQueryService.findPromotionByComparisonSales(1, 2);
        assertEquals(mockData, result);
    }
}