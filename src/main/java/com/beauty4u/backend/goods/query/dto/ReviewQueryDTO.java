package com.beauty4u.backend.goods.query.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewQueryDTO {
    private Long reviewId;
    private String goodsName;
    private Integer reviewScore;
    private String reviewContent;
    private Date reviewCreatedDate;
}
