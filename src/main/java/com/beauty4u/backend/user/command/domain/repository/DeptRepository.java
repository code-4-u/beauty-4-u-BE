package com.beauty4u.backend.user.command.domain.repository;

import com.beauty4u.backend.user.command.domain.aggregate.Dept;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DeptRepository extends JpaRepository<Dept, String> {
    Optional<Dept> findByDeptCode(String deptCode);
}