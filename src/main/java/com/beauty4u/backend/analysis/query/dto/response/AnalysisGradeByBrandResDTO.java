package com.beauty4u.backend.analysis.query.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/* 등급별 브랜드 구매 횟수 응답 DTO */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnalysisGradeByBrandResDTO {
    private String brandCode;    /* 브랜드 코드 */
    private String brandName;    /* 브랜드 이름 */
    private Integer orderCount;  /* 주문 횟수 */
}
