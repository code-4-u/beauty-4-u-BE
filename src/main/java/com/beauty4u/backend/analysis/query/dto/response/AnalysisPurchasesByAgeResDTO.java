package com.beauty4u.backend.analysis.query.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/* 연령별 구매 비율 응답 DTO */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnalysisPurchasesByAgeResDTO {
    private int ten;                /* 10대 수 */
    private int twenty;             /* 20대 수 */
    private int thirty;             /* 30대 수 */
    private int forty;              /* 40대 수 */
    private int fifty;              /* 50대 수 */
    private int sixtyup;            /* 60대 이상 수 */
    private int totalAge;           /* 연령별 총 합계 */

    private double tenRadio;        /* 10대 비율 */
    private double twentyRadio;     /* 20대 비율 */
    private double thirtyRadio;     /* 30대 비율 */
    private double fortyRadio;      /* 40대 비율 */
    private double fiftyRadio;      /* 50대 비율 */
    private double sixtyupRadio;    /* 60대 이상 비율 */
}
