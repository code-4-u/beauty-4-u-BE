package com.beauty4u.backend.analysis.command.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AprioriResponse {
    @JsonProperty("analysis_id")
    private Long analysisId;
}
