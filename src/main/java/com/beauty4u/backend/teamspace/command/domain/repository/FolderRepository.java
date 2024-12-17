package com.beauty4u.backend.teamspace.command.domain.repository;

import com.beauty4u.backend.common.aggregate.StatusType;
import com.beauty4u.backend.teamspace.command.domain.aggregate.Folder;
import com.beauty4u.backend.teamspace.command.domain.aggregate.Teamspace;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FolderRepository extends JpaRepository<Folder, Long> {

    boolean existsByFolderNameAndTeamspaceIdAndPublishStatusAndTopFolderIdIsNull(
            @Size(max = 50) @NotNull String folderName,
            @NotNull Teamspace teamspaceId,
            StatusType publishStatus);

    boolean existsByFolderNameAndPublishStatusAndTopFolderId(
            @Size(max = 50) @NotNull String folderName,
            StatusType publishStatus,
            Folder topFolderId);
}
