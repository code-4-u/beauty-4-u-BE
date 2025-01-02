package com.beauty4u.backend.analysis.command.domain.repository;

import com.beauty4u.backend.analysis.command.domain.aggregate.Analysis;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnalysisRepository extends JpaRepository<Analysis, Long> {
}
