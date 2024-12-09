package com.beauty4u.backend.template.command.domain.service;

import com.beauty4u.backend.common.exception.CustomException;
import com.beauty4u.backend.common.exception.ErrorCode;
import com.beauty4u.backend.template.command.application.dto.TemplateReqDTO;
import com.beauty4u.backend.template.command.domain.aggregate.Template;
import com.beauty4u.backend.template.command.domain.repository.TemplateRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TemplateDomainService {

    private final TemplateRepository templateRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public void saveTemplate(TemplateReqDTO templateReqDTO) {

        Template template = modelMapper.map(templateReqDTO, Template.class);

        templateRepository.save(template);
    }

    @Transactional
    public void updateTemplate(Long templateId, TemplateReqDTO templateReqDTO) {

        String templateTitle = templateReqDTO.getTemplateName();
        String templateContent = templateReqDTO.getTemplateContent();

        Template template = templateRepository.findById(templateId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_TEMPLATE));

        template.updateTemplate(templateTitle, templateContent);
    }

    @Transactional
    public void deleteTemplate(Long templateId) {

        Template template = templateRepository.findById(templateId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_TEMPLATE));

        templateRepository.delete(template);
    }
}
