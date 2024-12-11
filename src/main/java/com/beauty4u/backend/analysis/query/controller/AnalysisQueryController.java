package com.beauty4u.backend.analysis.query.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name="Analysis", description = "분석 대시보드 그래프 관련 값 조회 API")
@RequestMapping("/api/v1/analysis")
public class AnalysisQueryController {


}
