package com.beauty4u.backend.analysis.command.domain.aggregate;

import com.beauty4u.backend.common.aggregate.entity.CreatedTimeEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
@Entity
@Table(name = "analysis")
public class Analysis extends CreatedTimeEntity {

    @Id
    @Column(name = "analysis_id", nullable = false)
    private Long id;

    @NotNull
    @Lob
    @Enumerated(EnumType.STRING)
    private AnalysisKind analysisKind;

    @Size(max = 50)
    @NotNull
    @Column(name = "analysis_title", nullable = false, length = 50)
    private String analysisTitle;

    @NotNull
    @Lob
    @Column(name = "analysis_description", nullable = false)
    private String analysisDescription;
}