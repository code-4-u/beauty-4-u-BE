package com.beauty4u.backend.inquiry.command.domain.repository;

import com.beauty4u.backend.inquiry.command.domain.aggregate.Inquiry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InquiryRepository extends JpaRepository<Inquiry, Long> {
}
