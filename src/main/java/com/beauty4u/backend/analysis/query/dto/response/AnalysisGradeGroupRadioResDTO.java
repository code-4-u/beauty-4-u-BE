package com.beauty4u.backend.analysis.query.dto.response;

import lombok.Getter;
import lombok.Setter;

/* 등급별 구매 비율 (원형 그래프) 조회 */
@Getter
@Setter
public class AnalysisGradeGroupRadioResDTO {
    private Integer gold;       /* 골드 등급 수 */
    private Integer black;      /* 블랙 등급 수 */
    private Integer green;      /* 그린 등급 수 */
    private Integer pink;       /* 핑크 등급 수 */
    private Integer baby;       /* 베이비 등급 수 */
    private Double goldRadio;   /* 골드 등급 비율 */
    private Double blackRadio;  /* 블랙 등급 비율 */
    private Double greenRadio;  /* 그린 등급 비율 */
    private Double pinkRadio;   /* 핑크 등급 비율 */
    private Double babyRadio;   /* 베이비 등급 비율 */
}
