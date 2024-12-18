package com.beauty4u.backend.template.command.application.controller;

import com.beauty4u.backend.common.response.ApiResponse;
import com.beauty4u.backend.common.response.ResponseUtil;
import com.beauty4u.backend.common.success.SuccessCode;
import com.beauty4u.backend.template.command.application.dto.TemplateReqDTO;
import com.beauty4u.backend.template.command.application.service.TemplateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/template")
@Tag(name = "Template", description = "템플릿 관련 API")
public class TemplateCommandController {

    private final TemplateService templateService;

    @Operation(summary = "템플릿 등록", description = "템플릿을 등록한다.")
    @PostMapping
    public ResponseEntity<ApiResponse<TemplateReqDTO>> saveTemplate(
            @RequestBody @Valid TemplateReqDTO templateReqDTO) {

        templateService.saveTemplate(templateReqDTO);

        return ResponseUtil.successResponse(SuccessCode.TEMPLATE_SAVE_SUCCESS);
    }

    @Operation(summary = "템플릿 수정", description = "템플릿을 수정한다.")
    @PutMapping("/{templateId}")
    public ResponseEntity<ApiResponse<TemplateReqDTO>> updateTemplate(
            @PathVariable Long templateId,
            @RequestBody @Valid TemplateReqDTO templateReqDTO) {

        templateService.updateTemplate(templateId, templateReqDTO);

        return ResponseUtil.successResponse(SuccessCode.TEMPLATE_UPDATE_SUCCESS);
    }

    @Operation(summary = "템플릿 삭제", description = "템플릿을 삭제한다.")
    @DeleteMapping("/{templateId}")
    public ResponseEntity<ApiResponse<Void>> deleteTemplate(
            @PathVariable Long templateId) {

        templateService.deleteTemplate(templateId);

        return ResponseUtil.successResponse(SuccessCode.TEMPLATE_DELETE_SUCCESS);
    }
}
