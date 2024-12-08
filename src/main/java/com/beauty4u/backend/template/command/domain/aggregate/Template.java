package com.beauty4u.backend.template.command.domain.aggregate;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "template")
public class Template {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "template_id", nullable = false)
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
    private LocalDateTime templateCreatedDate;

    @Column(name = "template_updated_date")
    private LocalDateTime templateUpdatedDate;

    public void updateTemplate(String templateTitle, String templateContent, LocalDateTime templateUpdatedDate) {
        this.templateName = templateTitle;
        this.templateContent = templateContent;
        this.templateUpdatedDate = LocalDateTime.now();
    }
}