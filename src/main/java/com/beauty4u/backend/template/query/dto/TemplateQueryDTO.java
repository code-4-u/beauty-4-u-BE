package com.beauty4u.backend.template.query.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TemplateQueryDTO {

    private Long templateId;
    private String templateName;
    private String templateContent;
    private LocalDateTime createdDate;
}
