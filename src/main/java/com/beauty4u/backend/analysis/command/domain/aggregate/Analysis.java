package com.beauty4u.backend.analysis.command.domain.aggregate;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "analysis")
public class Analysis {
    @Id
    @Column(name = "analysis_id", nullable = false)
    private Long id;

    @NotNull
    @Lob
    @Column(name = "analysis_kind", nullable = false)
    private String analysisKind;

    @Size(max = 50)
    @NotNull
    @Column(name = "analysis_title", nullable = false, length = 50)
    private String analysisTitle;

    @NotNull
    @Lob
    @Column(name = "analysis_description", nullable = false)
    private String analysisDescription;

    @NotNull
    @Column(name = "analysis_created_date", nullable = false)
    private Instant analysisCreatedDate;

}