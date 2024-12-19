package com.beauty4u.backend.analysis.query.service;

import com.beauty4u.backend.analysis.query.dto.response.*;
import com.beauty4u.backend.analysis.query.mapper.AnalysisQueryMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AnalysisQueryServiceTest {

    @Mock
    private AnalysisQueryMapper analysisQueryMapper;

    @InjectMocks
    private AnalysisQueryService analysisQueryService;

    LocalDateTime testStartDate = LocalDateTime.of(2023, 1, 1, 0, 0);
    LocalDateTime testEndDate = LocalDateTime.of(2023, 3, 31, 0, 0);

    @Test
    @DisplayName("고객 비율 분석 (연령별 성별 비율 분석) 조회 로직 테스트")
    void selectAnalysisAgeGroupRadio() {
        AnalysisAgeGroupRadioResDTO mockData = AnalysisAgeGroupRadioResDTO.builder()
                .totalAge(2000)
                .totalTenAge(342)
                .tenMale(170)
                .tenFemale(172)
                .totalTwentyAge(376)
                .twentyMale(190)
                .twentyFemale(186)
                .totalThirtyAge(431)
                .thirtyMale(210)
                .thirtyFemale(221)
                .totalFortyAge(404)
                .fortyMale(205)
                .fortyFemale(199)
                .totalFiftyAge(407)
                .fiftyMale(195)
                .fiftyFeMale(212)
                .totalSixtyupAge(40)
                .sixtyupMale(20)
                .sixtyupFemale(20)
                .customerTenMaleRadio(49.71)
                .customerTenFemaleRadio(50.29)
                .customerTwentyMaleRadio(50.23)
                .customerTwentyFemaleRadio(49.47)
                .customerThirtyMaleRadio(48.72)
                .customerThirtyFemaleRadio(51.28)
                .customerFortyMaleRadio(50.74)
                .customerFortyFemaleRadio(49.26)
                .customerFiftyMaleRadio(47.91)
                .customerFiftyFemaleRadio(52.09)
                .customerSixtyupMaleRadio(50.00)
                .customerSixtyupFemaleRadio(50.00)
                .build();

        when(analysisQueryMapper.selectAnalysisAgeGroupRadio()).thenReturn(mockData);
        AnalysisAgeGroupRadioResDTO result = analysisQueryService.selectAnalysisAgeGroupRadio();
        assertEquals(mockData, result);
    }

    @Test
    @DisplayName("연령별 구매 비율 구하기 (원형 그래프) 조회 로직 테스트")
    void selectAnalysisPurchasesByAge() {
        AnalysisPurchasesByAgeResDTO mockData = AnalysisPurchasesByAgeResDTO.builder()
                .ten(167)
                .twenty(191)
                .thirty(210)
                .forty(204)
                .fifty(219)
                .sixtyup(29)
                .totalAge(1020)
                .tenRadio(16.4)
                .twentyRadio(18.7)
                .thirtyRadio(20.6)
                .fortyRadio(20.0)
                .fiftyRadio(21.5)
                .sixtyupRadio(2.8)
                .build();

        when(analysisQueryMapper.selectAnalysisPurchasesByAge(testStartDate, testEndDate)).thenReturn(mockData);

        AnalysisPurchasesByAgeResDTO result = analysisQueryService.selectAnalysisPurchasesByAge(testStartDate, testEndDate);

        assertEquals(mockData, result);
    }

    @Test
    @DisplayName("연령대 별 브랜드 구매 횟수 (수평 막대) 조회 로직 테스트")
    void selectAnalysisPurchasesByBrand() {
        List<AnalysisPurchasesByBrandResDTO> mockData = Arrays.asList(
                new AnalysisPurchasesByBrandResDTO("B005", "스킨푸드", 42),
                new AnalysisPurchasesByBrandResDTO("B003", "스킨케어", 33),
                new AnalysisPurchasesByBrandResDTO("B002", "스킨올드", 31),
                new AnalysisPurchasesByBrandResDTO("B001", "스킨뉴비", 30)
        );

        when(analysisQueryMapper.selectAnalysisPurchasesByBrand(10, 19, testStartDate, testEndDate)).thenReturn(mockData);

        List<AnalysisPurchasesByBrandResDTO> result = analysisQueryService.selectAnalysisPurchasesByBrand(10, 19, testStartDate, testEndDate);
        assertEquals(mockData, result);
    }

    @Test
    @DisplayName("연령별 브랜드 별 제품 구매 횟수 (막대 그래프) 조회 로직 테스트")
    void selectAnalysisPurchasesByBrandProduct() {
        List<AnalysisPurchasesByBrandProductResDTO> mockData = Arrays.asList(
                new AnalysisPurchasesByBrandProductResDTO("합계", 123),
                new AnalysisPurchasesByBrandProductResDTO("피부에", 90),
                new AnalysisPurchasesByBrandProductResDTO("양보", 21),
                new AnalysisPurchasesByBrandProductResDTO("하세요", 12)
        );

        when(analysisQueryMapper.selectAnalysisPurchasesByBrandProduct("B001",10, 19, testStartDate, testEndDate)).thenReturn(mockData);

        List<AnalysisPurchasesByBrandProductResDTO> result = analysisQueryService.selectAnalysisPurchasesByBrandProduct("B001",10, 19, testStartDate, testEndDate);
        assertEquals(mockData, result);
    }

    @Test
    @DisplayName("등급별 구매 비울 (원형 그래프) 조회 로직 테스트")
    void selectAnalysisGradeGroupRadio() {
        AnalysisGradeGroupRadioResDTO mockData = AnalysisGradeGroupRadioResDTO.builder()
                .gold(100)
                .black(100)
                .green(100)
                .pink(100)
                .baby(100)
                .goldRadio(20.0)
                .blackRadio(20.0)
                .greenRadio(20.0)
                .pinkRadio(20.0)
                .babyRadio(20.0)
                .build();

        when(analysisQueryMapper.selectAnalysisGradeGroupRadio(testStartDate, testEndDate)).thenReturn(mockData);

        AnalysisGradeGroupRadioResDTO result = analysisQueryService.selectAnalysisGradeGroupRadio(testStartDate, testEndDate);
        assertEquals(mockData, result);
    }

    @Test
    @DisplayName("등급별 브랜드 구매 횟수 (막대 그래프) 조회 로직 테스트")
    void selectAnalysisGradeByBrand() {
        List<AnalysisGradeByBrandResDTO> mockData = Arrays.asList(
                new AnalysisGradeByBrandResDTO("B001", "미야", 123),
                new AnalysisGradeByBrandResDTO("B002", "마야", 100),
                new AnalysisGradeByBrandResDTO("B003", "호야", 90)
        );

        when(analysisQueryMapper.selectAnalysisGradeByBrand("GOLD", testStartDate, testEndDate)).thenReturn(mockData);

        List<AnalysisGradeByBrandResDTO> result = analysisQueryService.selectAnalysisGradeByBrand("GOLD", testStartDate, testEndDate);

        assertEquals(mockData, result);
    }

    @Test
    @DisplayName("등급별 브랜드 별 제품 구매 횟수 (수평 막대 그래프) 조회 로직 테스트")
    void selectAnalysisGradeByBrandProduct() {
        List<AnalysisGradeByBrandProductResDTO> mockData = Arrays.asList(
                new AnalysisGradeByBrandProductResDTO("합계", 300),
                new AnalysisGradeByBrandProductResDTO("스킨케어", 100),
                new AnalysisGradeByBrandProductResDTO("미야", 100),
                new AnalysisGradeByBrandProductResDTO("아모레", 100)
        );

        LocalDateTime testStartDate = LocalDateTime.of(2023, 1, 1, 0, 0);
        LocalDateTime testEndDate = LocalDateTime.of(2023, 3, 31, 0, 0);

        when(analysisQueryMapper.selectAnalysisGradeByBrandProduct("B001", "GOLD", testStartDate, testEndDate)).thenReturn(mockData);

        List<AnalysisGradeByBrandProductResDTO> result = analysisQueryService.selectAnalysisGradeByBrandProduct("B001", "GOLD", testStartDate, testEndDate);
        assertEquals(mockData, result);
    }

    @Test
    @DisplayName("연령별 매출 비율 (원형그래프) 조회 로직 테스트")
    void selectAnalysisAgeSalesRadio() {
        AnalysisAgeSalesRadioResDTO mockData = AnalysisAgeSalesRadioResDTO.builder()
                .totalSales(1000000)
                .tenSales(10000)
                .twentySales(100000)
                .thirtySales(100000)
                .fortySales(10000)
                .fiftySales(10000)
                .sixtySales(10000)
                .tenSalesRadio(10.0)
                .twentySalesRadio(10.0)
                .thirtySalesRadio(10.0)
                .fortySalesRadio(10.0)
                .fiftySalesRadio(10.0)
                .sixtySalesRadio(10.0)
                .build();

        when(analysisQueryMapper.selectAnalysisAgeSalesRadio(testStartDate, testEndDate)).thenReturn(mockData);

        AnalysisAgeSalesRadioResDTO result = analysisQueryService.selectAnalysisAgeSalesRadio(testStartDate, testEndDate);
        assertEquals(mockData, result);
    }

    @Test
    @DisplayName("연령별 브랜드 매출 조회 (수평 막대 그래프) 로직 테스트")
    void selectAnalysisAgeSalesByBrand() {
        List<AnalysisAgeSalesByBrandResDTO> mockData = Arrays.asList(
                new AnalysisAgeSalesByBrandResDTO("B001", "미야", 100000),
                new AnalysisAgeSalesByBrandResDTO("B002", "우야", 100000),
                new AnalysisAgeSalesByBrandResDTO("B003", "조야", 100000)
        );

       // when(analysisQueryMapper.selectAnalysisAgeSalesByBrand(testStartDate, testEndDate)).thenReturn(mockData);

        //List<AnalysisAgeSalesByBrandResDTO> result = analysisQueryService.selectAnalysisAgeSalesByBrand(testStartDate, testEndDate);

        //assertEquals(mockData, result);
    }

    @Test
    @DisplayName("연령별 브랜드 제품별 매출 조회 로직 테스트")
    void selectAnalysisAgeSalesByBrandProduct() {
        List<AnalysisAgeSalesByBrandProductResDTO> mockData = Arrays.asList(
                new AnalysisAgeSalesByBrandProductResDTO("아이좋아", 1560000),
                new AnalysisAgeSalesByBrandProductResDTO("아이조아", 1560000),
                new AnalysisAgeSalesByBrandProductResDTO("아이히아", 1560000)
        );

        when(analysisQueryMapper.selectAnalysisAgeSalesByBrandProduct("B001", 10, 19, testStartDate, testEndDate)).thenReturn(mockData);

        List<AnalysisAgeSalesByBrandProductResDTO> result = analysisQueryService.selectAnalysisAgeSalesByBrandProduct("B001", 10, 19, testStartDate, testEndDate);

        assertEquals(mockData, result);
    }

    @Test
    @DisplayName("등급별 매출 비율 조회 로직 테스트")
    void selectAnalysisGradeSalesRadio() {
        AnalysisGradeSalesRadioDTO mockData = AnalysisGradeSalesRadioDTO.builder()
                .totalSales(10000)
                .goldSales(10000)
                .blackSales(10000)
                .greenSales(10000)
                .pinkSales(10000)
                .babySales(10000)
                .goldSalesRadio(10.0)
                .blackSalesRadio(10.0)
                .greenSalesRadio(10.0)
                .pinkSalesRadio(10.0)
                .babySalesRadio(10.0)
                .build();

        when(analysisQueryMapper.selectAnalysisGradeSalesRadio(testStartDate, testEndDate)).thenReturn(mockData);

        AnalysisGradeSalesRadioDTO result = analysisQueryService.selectAnalysisGradeSalesRadio(testStartDate, testEndDate);

        assertEquals(mockData, result);
    }

    @Test
    @DisplayName("등급별 브랜드 매출 조회 로직 테스트")
    void selectAnalysisGradeSalesByBrand() {
        List<AnalysisGradeSalesByBrandResDTO> mockData = Arrays.asList(
            new AnalysisGradeSalesByBrandResDTO("B001", "아뜰", 100000),
            new AnalysisGradeSalesByBrandResDTO("B002", "아흑", 100000),
            new AnalysisGradeSalesByBrandResDTO("B003", "아힝", 100000)
        );

        when(analysisQueryMapper.selectAnalysisGradeSalesByBrand("B001", testStartDate, testEndDate)).thenReturn(mockData);

        List<AnalysisGradeSalesByBrandResDTO> result = analysisQueryService.selectAnalysisGradeSalesByBrand("B001", testStartDate, testEndDate);

        assertEquals(mockData, result);
    }

    @Test
    @DisplayName("등급별 브랜드별 제품 매출 조회 로직 테스트")
    void selectAnalysisGradeSalesByBrandProduct() {
        List<AnalysisGradeSalesByBrandProductResDTO> mockData = Arrays.asList(
            new AnalysisGradeSalesByBrandProductResDTO("요호", 100000),
            new AnalysisGradeSalesByBrandProductResDTO("요히", 100000),
            new AnalysisGradeSalesByBrandProductResDTO("요후", 100000)
        );

        when(analysisQueryMapper.selectAnalysisGradeSalesByBrandProduct("B001", "GOLD", testStartDate, testEndDate)).thenReturn(mockData);

        List<AnalysisGradeSalesByBrandProductResDTO> result = analysisQueryService.selectAnalysisGradeSalesByBrandProduct("B001", "GOLD", testStartDate, testEndDate);

        assertEquals(mockData, result);
    }
}