package com.beauty4u.backend.analysis.command.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AprioriResDTO {

    private String goodsCode;
    private String goodsName;
    private String associatedGoodsCode;
    private String associatedGoodsName;
    private double support;
    private double confidence;
    private double lift;
    private double totalScore;
}
