package com.beauty4u.backend.file.command.domain.repository;

import com.beauty4u.backend.file.command.domain.aggregate.FileInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FileRepository extends JpaRepository<FileInfo, Long> {
    void deleteAllByFileS3UrlIn(List<String> fileS3UrlList);
}
