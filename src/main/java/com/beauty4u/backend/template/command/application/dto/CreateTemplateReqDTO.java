package com.beauty4u.backend.template.command.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateTemplateReqDTO {
    private String templateName;
    private String templateContent;
    private LocalDateTime templateCreatedDate;
}
