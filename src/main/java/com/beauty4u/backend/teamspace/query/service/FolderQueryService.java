package com.beauty4u.backend.teamspace.query.service;

import com.beauty4u.backend.teamspace.query.dto.folder.FindFolderListFilterDTO;
import com.beauty4u.backend.teamspace.query.dto.folder.FolderListResDTO;
import com.beauty4u.backend.teamspace.query.mapper.FolderQueryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FolderQueryService {

    private final FolderQueryMapper folderQueryMapper;

    public List<FolderListResDTO> findFolderList(FindFolderListFilterDTO findFolderListFilterDTO) {

        return folderQueryMapper.findFolderList(
                findFolderListFilterDTO.getTopFolderId(),
                findFolderListFilterDTO.getTeamspaceId(),
                findFolderListFilterDTO.getFolderStatus(),
                findFolderListFilterDTO.getFolderName()
        );
    }
}
