package com.beauty4u.backend.template.query.controller;

import com.beauty4u.backend.common.response.ApiResponse;
import com.beauty4u.backend.common.response.ResponseUtil;
import com.beauty4u.backend.common.success.SuccessCode;
import com.beauty4u.backend.template.query.dto.TemplateQueryDTO;
import com.beauty4u.backend.template.query.service.TemplateQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/template")
@Tag(name="Template", description = "템플릿 API")
public class TemplateQueryController {

    private final TemplateQueryService templateQueryService;

    @GetMapping("/list")
    @Operation(summary = "템플릿 목록 조회", description = "템플릿 전체 목록을 조회한다.")
    public ResponseEntity<ApiResponse<List<TemplateQueryDTO>>> findAllTemplate(){
        List<TemplateQueryDTO> templateList = templateQueryService.findAllTemplate();

        return ResponseUtil.successResponse(SuccessCode.TEMPLATE_FIND_LIST_SUCCESS, templateList);
    }
}
