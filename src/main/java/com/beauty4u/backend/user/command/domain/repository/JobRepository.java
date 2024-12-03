package com.beauty4u.backend.user.command.domain.repository;

import com.beauty4u.backend.user.command.domain.aggregate.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, String> {
}