package com.beauty4u.backend.template.command.domain.repository;

import com.beauty4u.backend.template.command.domain.aggregate.Template;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TemplateRepository extends JpaRepository<Template, Long> {
}
