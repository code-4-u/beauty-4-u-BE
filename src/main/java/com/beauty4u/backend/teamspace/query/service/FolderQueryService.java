package com.beauty4u.backend.teamspace.query.service;

import com.beauty4u.backend.common.exception.CustomException;
import com.beauty4u.backend.common.exception.ErrorCode;
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

        List<FolderListResDTO> folderList = null;

        try {
            folderList = folderQueryMapper.findFolderList(
                    findFolderListFilterDTO.getTopFolderId(),
                    findFolderListFilterDTO.getTeamspaceId(),
                    findFolderListFilterDTO.getFolderStatus(),
                    findFolderListFilterDTO.getFolderName()
            );
        } catch (Exception e) {
            throw new CustomException(ErrorCode.FOLDER_LIST_NOT_FOUND);
        }

        return folderList;
    }
}
