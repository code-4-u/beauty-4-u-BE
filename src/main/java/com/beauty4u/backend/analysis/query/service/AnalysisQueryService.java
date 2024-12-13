package com.beauty4u.backend.analysis.query.service;

import com.beauty4u.backend.analysis.query.dto.response.*;
import com.beauty4u.backend.analysis.query.mapper.AnalysisQueryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/* 데이터 분석 그래프 그리기 위한 데이터 호출 서비스 */
@Service
@RequiredArgsConstructor
public class AnalysisQueryService {

    /* 매퍼 생성 */
    private final AnalysisQueryMapper analysisQueryMapper;

    /* 고객 비율 분석 (연령별 성별 비율 분석) 조회 서비스 */
    @Transactional(readOnly = true)
    public AnalysisAgeGroupRadioResDTO selectAnalysisAgeGroupRadio() {
        return analysisQueryMapper.selectAnalysisAgeGroupRadio();
    }

    /* 연령별 구매 비율 구하기 (원형 그래프) 조회 */
    @Transactional(readOnly = true)
    public AnalysisPurchasesByAgeResDTO selectAnalysisPurchasesByAge(LocalDateTime startDate, LocalDateTime endDate) {
        return analysisQueryMapper.selectAnalysisPurchasesByAge(startDate, endDate);
    }

    /* 원형 그래프 클릭 시 연령별 브랜드 별 구매 횟수 (수평 막대 그래프) 조회 */
    @Transactional(readOnly = true)
    public List<AnalysisPurchasesByBrandResDTO> selectAnalysisPurchasesByBrand(Integer startAge, Integer endAge,
                                                                               LocalDateTime startDate, LocalDateTime endDate) {
        return analysisQueryMapper.selectAnalysisPurchasesByBrand(startAge, endAge, startDate, endDate);
    }

    /* 해당 브랜드의 제품별 구매 횟수 (수평 막대 그래프) 조회 */
    @Transactional(readOnly = true)
    public List<AnalysisPurchasesByBrandProductResDTO> selectAnalysisPurchasesByBrandProduct(String brandCode, Integer startAge,
                                                                                             Integer endAge, LocalDateTime startDate,
                                                                                             LocalDateTime endDate) {
        return analysisQueryMapper.selectAnalysisPurchasesByBrandProduct(brandCode, startAge, endAge, startDate, endDate);
    }

    /* 등급별 구매 비율 (원형 그래프) 조회 */
    @Transactional(readOnly = true)
    public AnalysisGradeGroupRadioResDTO selectAnalysisGradeGroupRadio(LocalDateTime startDate, LocalDateTime endDate) {
        return analysisQueryMapper.selectAnalysisGradeGroupRadio(startDate, endDate);
    }

    /* 등급별 브랜드 구매 횟수 (막대 그래프) 조회 */
    @Transactional(readOnly = true)
    public List<AnalysisGradeByBrandResDTO> selectAnalysisGradeByBrand(String grade,
                                                                       LocalDateTime startDate,
                                                                       LocalDateTime endDate) {
        return analysisQueryMapper.selectAnalysisGradeByBrand(grade, startDate, endDate);
    }

    /* 등급별 브랜드 별 제품 구매 횟수 (막대 그래프) 조회 */
    @Transactional(readOnly = true)
    public List<AnalysisGradeByBrandProductResDTO> selectAnalysisGradeByBrandProduct(String brandCode,
                                                                                     String grade,
                                                                                     LocalDateTime startDate,
                                                                                     LocalDateTime endDate) {
        return analysisQueryMapper.selectAnalysisGradeByBrandProduct(brandCode, grade, startDate, endDate);
    }

    /* 연령별 매출 비율 (원형 그래프) 조회 */
    @Transactional(readOnly = true)
    public AnalysisAgeSalesRadioResDTO selectAnalysisAgeSalesRadio(LocalDateTime startDate, LocalDateTime endDate) {
        return analysisQueryMapper.selectAnalysisAgeSalesRadio(startDate, endDate);
    }

    /* 연령별 브랜드 매출 조회 (수평 막대 그래프) */
    @Transactional(readOnly = true)
    public List<AnalysisAgeSalesByBrandResDTO> selectAnalysisAgeSalesByBrand(LocalDateTime startDate, LocalDateTime endDate) {
        return analysisQueryMapper.selectAnalysisAgeSalesByBrand(startDate, endDate);
    }

    /* 연령별 브랜드 제품별 매출 조회 (막대 그래프) */
    @Transactional(readOnly = true)
    public List<AnalysisAgeSalesByBrandProductResDTO> selectAnalysisAgeSalesByBrandProduct(String brandCode, Integer startAge,
                                                                                           Integer endAge, LocalDateTime startDate,
                                                                                           LocalDateTime endDate) {
        return analysisQueryMapper.selectAnalysisAgeSalesByBrandProduct(brandCode, startAge, endAge, startDate, endDate);
    }

    /* 등급별 매출 비율 (원형그래프) 조회 */
    @Transactional(readOnly = true)
    public AnalysisGradeSalesRadioDTO selectAnalysisGradeSalesRadio(LocalDateTime startDate, LocalDateTime endDate) {
        return analysisQueryMapper.selectAnalysisGradeSalesRadio(startDate, endDate);
    }

    /* 등급별 브랜드 매출 조회 (수평 막대 그래프) */
    @Transactional(readOnly = true)
    public List<AnalysisGradeSalesByBrandResDTO> selectAnalysisGradeSalesByBrand(String brandCode, LocalDateTime startDate, LocalDateTime endDate) {
        return analysisQueryMapper.selectAnalysisGradeSalesByBrand(brandCode, startDate, endDate);
    }

    /* 등급별 브랜드별 제품 매출 조회 (수평 막대 그래프) */
    @Transactional(readOnly = true)
    public List<AnalysisGradeSalesByBrandProductResDTO> selectAnalysisGradeSalesByBrandProduct(String brandCode, String gradeCode,
                                                                                               LocalDateTime startDate,
                                                                                               LocalDateTime endDate) {
        return analysisQueryMapper.selectAnalysisGradeSalesByBrandProduct(brandCode, gradeCode, startDate, endDate);
    }
}
