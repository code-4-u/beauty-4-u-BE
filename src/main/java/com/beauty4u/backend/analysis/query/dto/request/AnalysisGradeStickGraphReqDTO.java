package com.beauty4u.backend.analysis.query.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnalysisGradeStickGraphReqDTO {
    private String brandCode;
    private String grade;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
