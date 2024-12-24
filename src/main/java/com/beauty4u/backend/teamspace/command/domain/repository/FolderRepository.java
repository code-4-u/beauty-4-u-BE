package com.beauty4u.backend.teamspace.command.domain.repository;

import com.beauty4u.backend.common.aggregate.StatusType;
import com.beauty4u.backend.teamspace.command.domain.aggregate.Folder;
import com.beauty4u.backend.teamspace.command.domain.aggregate.ChatRoom;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FolderRepository extends JpaRepository<Folder, Long> {

    boolean existsByFolderNameAndChatRoomIdAndPublishStatusAndTopFolderIdIsNull(
            @Size(max = 50) @NotNull String folderName,
            @NotNull ChatRoom chatRoomId,
            StatusType publishStatus);

    boolean existsByFolderNameAndPublishStatusAndTopFolderId(
            @Size(max = 50) @NotNull String folderName,
            StatusType publishStatus,
            Folder topFolderId);
}
