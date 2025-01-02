package com.beauty4u.backend.analysis.command.application.dto;

import lombok.Data;

@Data
public class AprioriRequest {
    private String goodsCode;
    private String analysisKind = "ASSOCIATION";
    private String analysisTitle;
    private String analysisDescription;
}
