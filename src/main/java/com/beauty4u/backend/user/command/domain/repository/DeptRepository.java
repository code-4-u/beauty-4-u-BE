package com.beauty4u.backend.user.command.domain.repository;

import com.beauty4u.backend.user.command.domain.aggregate.Dept;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeptRepository extends JpaRepository<Dept, String> {
}