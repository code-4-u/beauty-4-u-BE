package com.beauty4u.backend.teamspace.command.application.service;

import com.beauty4u.backend.common.exception.CustomException;
import com.beauty4u.backend.common.exception.ErrorCode;
import com.beauty4u.backend.teamspace.command.application.dto.folder.UpdateFolderDTO;
import com.beauty4u.backend.teamspace.command.application.dto.folder.saveFolderDTO;
import com.beauty4u.backend.teamspace.command.application.dto.teamspace.FindTeamspaceDTO;
import com.beauty4u.backend.teamspace.command.domain.aggregate.Folder;
import com.beauty4u.backend.teamspace.command.domain.aggregate.Teamspace;
import com.beauty4u.backend.teamspace.command.domain.service.FolderDomainService;
import com.beauty4u.backend.teamspace.command.domain.service.TeamspaceDomainService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class FolderService {

    private final TeamspaceDomainService teamspaceDomainService;
    private final FolderDomainService folderDomainService;
    private final ModelMapper modelMapper;

    @Transactional
    public void saveFolder(saveFolderDTO saveFolderDTO) {

        FindTeamspaceDTO findTeamspaceDTO = teamspaceDomainService.findTeamspaceById(saveFolderDTO.getTeamspaceId());

        Teamspace teamspace = modelMapper.map(findTeamspaceDTO, Teamspace.class);

        // 부모 폴더 조회
        Folder topFolder = saveFolderDTO.getTopFolderId() != null ?
                modelMapper.map(folderDomainService.findById(saveFolderDTO.getTopFolderId()), Folder.class) : null;

        // Folder 객체 생성
        Folder folder = Folder.builder()
                .teamspaceId(teamspace)                 // 팀스페이스 설정
                .topFolderId(topFolder)           // 부모 폴더 설정
                .folderName(saveFolderDTO.getFolderName())  // 폴더 이름 설정
                .build();

        // 폴더 저장
        folderDomainService.saveFolder(folder);
    }

    @Transactional
    public void updateFolder(Long folderId, UpdateFolderDTO updateFolderDTO) {

        try {
            folderDomainService.updateFolder(folderId, updateFolderDTO);
        } catch (CustomException e) {
            throw e;
        } catch (Exception e) {
            throw new CustomException(ErrorCode.FOLDER_UPDATE_FAIL);
        }
    }
}
