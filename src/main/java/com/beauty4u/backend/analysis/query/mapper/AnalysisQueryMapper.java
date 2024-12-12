package com.beauty4u.backend.analysis.query.mapper;

import com.beauty4u.backend.analysis.query.dto.response.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface AnalysisQueryMapper {

    /* 고객 비율 분석 (연령별 성별 비율 분석) 조회*/
    AnalysisAgeGroupRadioResDTO selectAnalysisAgeGroupRadio(@Param("startDate") LocalDateTime startDate,
                                                            @Param("endDate") LocalDateTime endDate);

    /* 연령별 구매 비율 구하기 (원형 그래프) 조회 */
    AnalysisPurchasesByAgeResDTO selectAnalysisPurchasesByAge(@Param("startDate") LocalDateTime startDate,
                                                              @Param("endDate") LocalDateTime endDate);

    /* 원형 그래프 클릭 시 연령별 브랜드 별 구매 횟수 (수평 막대 그래프) 조회 */
    List<AnalysisPurchasesByBrandResDTO> selectAnalysisPurchasesByBrand(@Param("startAge") Integer startAge,
                                                                        @Param("endAge") Integer endAge,
                                                                        @Param("startDate") LocalDateTime startDate,
                                                                        @Param("endDate") LocalDateTime endDate);

    /* 해당 브랜드의 제품별 구매 횟수(수평 막대 그래프) 조회 응답 */
    List<AnalysisPurchasesByBrandProductResDTO> selectAnalysisPurchasesByBrandProduct(@Param("brandCode") String brandCode,
                                                                                      @Param("startAge") Integer startAge,
                                                                                      @Param("endAge") Integer endAge,
                                                                                      @Param("startDate") LocalDateTime startDate,
                                                                                      @Param("endDate") LocalDateTime endDate);

    /* 등급별 구매 비율 (원형 그래프) 조회 */
    AnalysisGradeGroupRadioResDTO selectAnalysisGradeGroupRadio(@Param("startDate") LocalDateTime startDate,
                                                                @Param("endDate") LocalDateTime endDate);

    /* 등급별 브랜드 구매 횟수 (막대 그래프) 조회 */
    List<AnalysisGradeByBrandResDTO> selectAnalysisGradeByBrand(@Param("grade") String grade,
                                                                @Param("startDate") LocalDateTime startDate,
                                                                @Param("endDate") LocalDateTime endDate);

    /* 등급별 브랜드 별 제품 구매 횟수 (막대 그래프) 조회 */
    List<AnalysisGradeByBrandProductResDTO> selectAnalysisGradeByBrandProduct(@Param("brandCode") String brandCode,
                                                                              @Param("grade") String grade,
                                                                              @Param("startDate") LocalDateTime startDate,
                                                                              @Param("endDate") LocalDateTime endDate);

    /* 연령별 매출 비율 (원형그래프) 조회 */
    AnalysisAgeSalesRadioResDTO selectAnalysisAgeSalesRadio(@Param("startDate") LocalDateTime startDate,
                                                            @Param("endDate") LocalDateTime endDate);

    /* 연령별 브랜드 매출 조회 (수평 막대 그래프) */
    List<AnalysisAgeSalesByBrandResDTO> selectAnalysisAgeSalesByBrand(@Param("startDate") LocalDateTime startDate,
                                                                      @Param("endDate") LocalDateTime endDate);

    /* 연령별 브랜드 제품별 매출 조회 (막대 그래프) */
    List<AnalysisAgeSalesByBrandProductResDTO> selectAnalysisAgeSalesByBrandProduct(@Param("brandCode") String brandCode,
                                                                                    @Param("startAge") Integer startAge,
                                                                                    @Param("endAge") Integer endAge,
                                                                                    @Param("startDate") LocalDateTime startDate,
                                                                                    @Param("endDate") LocalDateTime endDate);

    /* 등급별 매출 비율 (원형그래프) 조회 */
    AnalysisGradeSalesRadioDTO selectAnalysisGradeSalesRadio(@Param("startDate") LocalDateTime startDate,
                                                             @Param("endDate") LocalDateTime endDate);

    /* 등급별 브랜드 매출 조회 (수평 막대 그래프) */
    List<AnalysisGradeSalesByBrandResDTO> selectAnalysisGradeSalesByBrand(@Param("brandCode") String brandCode,
                                                                          @Param("startDate") LocalDateTime startDate,
                                                                          @Param("endDate") LocalDateTime endDate);

    /* 등급별 브랜드별 제품 매출 조회 (수평 막대 그래프) */
    List<AnalysisGradeSalesByBrandProductResDTO> selectAnalysisGradeSalesByBrandProduct(@Param("brandCode") String brandCode,
                                                                                        @Param("gradeCode") String gradeCode,
                                                                                        @Param("startDate") LocalDateTime startDate,
                                                                                        @Param("endDate") LocalDateTime endDate);
}
