package com.beauty4u.backend.teamspace.command.domain.repository;

import com.beauty4u.backend.teamspace.command.domain.aggregate.Folder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FolderRepository extends JpaRepository<Folder, Long> {
    Folder findByFolderName(String name);
}
