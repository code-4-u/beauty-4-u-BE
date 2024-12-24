package com.beauty4u.backend.file.command.domain.repository;

import com.beauty4u.backend.file.command.domain.aggregate.FileInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<FileInfo, Long> {
}
