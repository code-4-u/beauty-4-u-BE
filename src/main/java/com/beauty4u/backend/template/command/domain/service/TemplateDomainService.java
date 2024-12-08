package com.beauty4u.backend.template.command.domain.service;

import com.beauty4u.backend.template.command.application.dto.CreateTemplateReqDTO;
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
    public void saveTemplate(CreateTemplateReqDTO createTemplateReqDTO) {
        Template template = modelMapper.map(createTemplateReqDTO, Template.class);
        templateRepository.save(template);
    }
}
