package com.beauty4u.backend.analysis.query.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnalysisAgeSalesByBrandResDTO {
    private String brandCode;        /* 브랜드 코드 */
    private String brandName;        /* 브랜드 이름 */
    private Integer totalOrderPrice; /* 총 매출 */
}
