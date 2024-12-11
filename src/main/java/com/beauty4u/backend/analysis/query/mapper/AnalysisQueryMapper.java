package com.beauty4u.backend.analysis.query.mapper;

import com.beauty4u.backend.analysis.query.dto.response.AnalysisAgeGroupRadioResDTO;
import com.beauty4u.backend.analysis.query.dto.response.AnalysisPurchasesByAgeResDTO;
import com.beauty4u.backend.analysis.query.dto.response.AnalysisPurchasesByBrandResDTO;
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
}
