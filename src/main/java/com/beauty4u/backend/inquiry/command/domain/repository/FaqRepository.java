package com.beauty4u.backend.inquiry.command.domain.repository;

import com.beauty4u.backend.inquiry.command.domain.aggregate.Faq;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FaqRepository extends JpaRepository<Faq, Long> {
}
