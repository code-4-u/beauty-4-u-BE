package com.beauty4u.backend.teamspace.query.mapper;

import com.beauty4u.backend.teamspace.query.dto.folder.FolderListResDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FolderQueryMapper {

    List<FolderListResDTO> findFolderList(
            @Param("topFolderId") Long topFolderId,
            @Param("teamspaceId") Long teamspaceId,
            @Param("folderStatus") String folderStatus,
            @Param("folderName") String folderName);
}
