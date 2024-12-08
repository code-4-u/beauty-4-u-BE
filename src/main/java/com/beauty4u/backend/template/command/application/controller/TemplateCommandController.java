package com.beauty4u.backend.template.command.application.controller;

import com.beauty4u.backend.common.response.ApiResponse;
import com.beauty4u.backend.common.response.ResponseUtil;
import com.beauty4u.backend.common.success.SuccessCode;
import com.beauty4u.backend.template.command.application.dto.CreateTemplateReqDTO;
import com.beauty4u.backend.template.command.application.service.TemplateCommandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/template")
@Tag(name = "Template", description = "템플릿 관련 API")
public class TemplateCommandController {
    private final TemplateCommandService templateCommandService;

    @Operation(summary = "템플릿 등록", description = "템플릿을 등록한다.")
    @PostMapping
    public ResponseEntity<ApiResponse<CreateTemplateReqDTO>> saveTemplate(
            @RequestBody CreateTemplateReqDTO createTemplateReqDTO) {
        templateCommandService.saveTemplate(createTemplateReqDTO);

        return ResponseUtil.successResponse(SuccessCode.TEMPLATE_SAVE_SUCCESS);
    }
}
