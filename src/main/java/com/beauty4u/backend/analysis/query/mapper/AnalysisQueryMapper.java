package com.beauty4u.backend.analysis.query.mapper;

import com.beauty4u.backend.analysis.query.dto.response.AnalysisAgeGroupRadioResDTO;
import com.beauty4u.backend.analysis.query.dto.response.AnalysisPurchasesByAgeResDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;

@Mapper
public interface AnalysisQueryMapper {

    /* 고객 비율 분석 (연령별 성별 비율 분석) */
    AnalysisAgeGroupRadioResDTO selectAnalysisAgeGroupRadio(@Param("startDate") LocalDateTime startDate,
                                                            @Param("endDate") LocalDateTime endDate);

    /* 연령별 구매 비율 구하기 (원형 그래프) */
    AnalysisPurchasesByAgeResDTO selectAnalysisPurchasesByAge(@Param("startDate") LocalDateTime startDate,
                                                              @Param("endDate") LocalDateTime endDate);


}
