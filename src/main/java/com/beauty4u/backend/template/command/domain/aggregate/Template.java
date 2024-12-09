package com.beauty4u.backend.template.command.domain.aggregate;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "template")
public class Template {

    @Id
    @Column(name = "template_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 100)
    @NotNull
    @Column(name = "template_name", nullable = false, length = 100)
    private String templateName;

    @NotNull
    @Lob
    @Column(name = "template_content", nullable = false)
    private String templateContent;

    @NotNull
    @Column(name = "template_created_date", nullable = false)
    private LocalDateTime templateCreatedDate = LocalDateTime.now();

    @Column(name = "template_updated_date")
    private LocalDateTime templateUpdatedDate;

    public void updateTemplate(String templateTitle, String templateContent) {
        this.templateName = templateTitle;
        this.templateContent = templateContent;
        this.templateUpdatedDate = LocalDateTime.now();
    }
}