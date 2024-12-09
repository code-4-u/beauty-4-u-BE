package com.beauty4u.backend.template.command.application.service;

import com.beauty4u.backend.template.command.application.dto.TemplateReqDTO;
import com.beauty4u.backend.template.command.domain.service.TemplateDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TemplateService {

    private final TemplateDomainService templateDomainService;

    public void saveTemplate(TemplateReqDTO templateReqDTO) {

        templateDomainService.saveTemplate(templateReqDTO);
    }

    public void updateTemplate(Long templateId, TemplateReqDTO templateReqDTO) {

        templateDomainService.updateTemplate(templateId, templateReqDTO);
    }

    public void deleteTemplate(Long templateId) {

        templateDomainService.deleteTemplate(templateId);
    }
}
