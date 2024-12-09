package com.beauty4u.backend.template.command.domain.service;

import com.beauty4u.backend.common.exception.CustomException;
import com.beauty4u.backend.common.exception.ErrorCode;
import com.beauty4u.backend.template.command.application.dto.CreateTemplateReqDTO;
import com.beauty4u.backend.template.command.application.dto.UpdateTemplateReqDTO;
import com.beauty4u.backend.template.command.domain.aggregate.Template;
import com.beauty4u.backend.template.command.domain.repository.TemplateRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TemplateDomainService {

    private final TemplateRepository templateRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public void saveTemplate(CreateTemplateReqDTO createTemplateReqDTO) {
        Template template = modelMapper.map(createTemplateReqDTO, Template.class);
        templateRepository.save(template);
    }

    @Transactional
    public void updateTemplate(Long templateId, UpdateTemplateReqDTO updateTemplateReqDTO) {

        String templateTitle = updateTemplateReqDTO.getTemplateName();
        String templateContent = updateTemplateReqDTO.getTemplateContent();
        LocalDateTime templateUpdateDate = updateTemplateReqDTO.getTemplateUpdatedDate();

        Template template = templateRepository.findById(templateId).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_TEMPLATE));

        template.updateTemplate(templateTitle, templateContent, templateUpdateDate);

    }

    @Transactional
    public void deleteTemplate(Long templateId) {

        Template template = templateRepository.findById(templateId).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_TEMPLATE));

        templateRepository.delete(template);
    }
}
