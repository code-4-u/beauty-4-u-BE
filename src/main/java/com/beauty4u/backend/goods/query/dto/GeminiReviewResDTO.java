package com.beauty4u.backend.goods.query.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GeminiReviewResDTO {

    private String goodsCode;
    private String goodsName;
    private String geminiReview; // 제미나이를 통해 받은 리뷰
}
