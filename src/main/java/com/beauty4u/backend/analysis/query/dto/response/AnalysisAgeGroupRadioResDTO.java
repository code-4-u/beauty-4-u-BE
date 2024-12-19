package com.beauty4u.backend.analysis.query.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/* 고객 비율 분석 (연령별 성별 비율 분석) 응답 DTO */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnalysisAgeGroupRadioResDTO {
    private Integer totalAge;                   /* 총 나이대 고객수 */

    private Integer totalTenAge;                /* 총 10대 수 */
    private Integer tenMale;                    /* 10대 남자 수 */
    private Integer tenFemale;                  /* 10대 여자 수 */

    private Integer totalTwentyAge;             /* 총 20대 수 */
    private Integer twentyMale;                 /* 20대 남자 수 */
    private Integer twentyFemale;               /* 20대 여자 수 */

    private Integer totalThirtyAge;             /* 총 30대 수 */
    private Integer thirtyMale;                 /* 30대 남자 수 */
    private Integer thirtyFemale;               /* 30대 여자 수 */

    private Integer totalFortyAge;              /* 총 40대 수 */
    private Integer fortyMale;                  /* 40대 남자 수 */
    private Integer fortyFemale;                /* 40대 여자 수 */

    private Integer totalFiftyAge;              /* 총 50대 수 */
    private Integer fiftyMale;                  /* 50대 남자 수 */
    private Integer fiftyFeMale;                /* 50대 여자 수 */

    private Integer totalSixtyupAge;            /* 총 60대 이상 수 */
    private Integer sixtyupMale;                /* 60대 이상 남자 수 */
    private Integer sixtyupFemale;              /* 60대 이상 여자 수 */

    private Double customerTenMaleRadio;        /* 10대 남자 비율 */
    private Double customerTenFemaleRadio;      /* 10대 여자 비율 */
    private Double customerTwentyMaleRadio;     /* 20대 남자 비율 */
    private Double customerTwentyFemaleRadio;   /* 20대 여자 비율 */
    private Double customerThirtyMaleRadio;     /* 30대 남자 비율 */
    private Double customerThirtyFemaleRadio;   /* 30대 여자 비율 */
    private Double customerFortyMaleRadio;      /* 40대 남자 비율 */
    private Double customerFortyFemaleRadio;    /* 40대 여자 비율 */
    private Double customerFiftyMaleRadio;      /* 50대 남자 비율 */
    private Double customerFiftyFemaleRadio;    /* 50대 여자 비율 */
    private Double customerSixtyupMaleRadio;    /* 60대 이상 남자 비율 */
    private Double customerSixtyupFemaleRadio;  /* 60대 이상 여자 비율 */
}
