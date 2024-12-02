package com.beauty4u.backend.basesystem.command.domain.aggregate;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "template")
public class Template {
    @Id
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
    private Instant templateCreatedDate;

    @Column(name = "template_updated_date")
    private Instant templateUpdatedDate;

}