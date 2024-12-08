package com.beauty4u.backend.template.command.application.service;

import com.beauty4u.backend.template.command.application.dto.CreateTemplateReqDTO;
import com.beauty4u.backend.template.command.domain.service.TemplateDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TemplateCommandService {

    private final TemplateDomainService templateDomainService;

    public void saveTemplate(CreateTemplateReqDTO createTemplateReqDTO) {
        templateDomainService.saveTemplate(createTemplateReqDTO);
    }
}
