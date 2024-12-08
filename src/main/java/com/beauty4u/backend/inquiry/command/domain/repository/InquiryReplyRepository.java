package com.beauty4u.backend.inquiry.command.domain.repository;

import com.beauty4u.backend.inquiry.command.domain.aggregate.InquiryReply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InquiryReplyRepository extends JpaRepository<InquiryReply, Long> {
}
