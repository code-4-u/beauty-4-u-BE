package com.beauty4u.backend.teamspace.command.domain.service;

import com.beauty4u.backend.common.aggregate.StatusType;
import com.beauty4u.backend.common.exception.CustomException;
import com.beauty4u.backend.common.exception.ErrorCode;
import com.beauty4u.backend.teamspace.command.application.dto.folder.FindFolderDTO;
import com.beauty4u.backend.teamspace.command.application.dto.folder.UpdateFolderDTO;
import com.beauty4u.backend.teamspace.command.application.dto.teamspace.FindTeamspaceDTO;
import com.beauty4u.backend.teamspace.command.domain.aggregate.Folder;
import com.beauty4u.backend.teamspace.command.domain.aggregate.Teamspace;
import com.beauty4u.backend.teamspace.command.domain.repository.FolderRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FolderDomainService {

    private final FolderRepository folderRepository;
    private final ModelMapper modelMapper;

    public void saveFolder(String folderName, FindFolderDTO topFolderDTO, FindTeamspaceDTO findTeamspaceDTO) {

        boolean isExistsSameFolderName;
        Folder topFolder = null;
        Teamspace teamspace = modelMapper.map(findTeamspaceDTO, Teamspace.class);

        // 부모 폴더가 없다면 -> 해당 폴더가 가장 상위 폴더
        if (topFolderDTO == null) {

            // 가장 상위 폴더에서 같은 이름이 있는지 확인
            isExistsSameFolderName = folderRepository
                    .existsByFolderNameAndTeamspaceIdAndPublishStatusAndTopFolderIdIsNull(
                            folderName,
                            teamspace,
                            StatusType.PUBLISHED);
        } else { // 부모 폴더가 있다면

            topFolder = modelMapper.map(topFolderDTO, Folder.class);
            // 해당 부모 폴더의 바로 하단 폴더에서 같은 이름의 폴더가 있는지 확인
            isExistsSameFolderName = folderRepository
                    .existsByFolderNameAndPublishStatusAndTopFolderId(
                            folderName,
                            StatusType.PUBLISHED,
                            topFolder);
        }

        // 폴더 이름이 겹치면 오류 던짐
        if (isExistsSameFolderName) {
            throw new CustomException(ErrorCode.FOLDER_SAME_NAME);
        }

        // 폴더 이름이 겹치지 않으면 저장 시도
        try {
            Folder newFolder = Folder.builder()
                    .teamspaceId(teamspace)  // 팀스페이스 설정
                    .topFolderId(topFolder)  // 부모 폴더 설정
                    .folderName(folderName)  // 폴더 이름 설정
                    .build();

            folderRepository.save(newFolder);
        } catch (Exception e) {
            throw new CustomException(ErrorCode.FOLDER_SAVE_FAIL);
        }
    }

    public FindFolderDTO findById(Long topFolderId) {

        Folder topFolder = folderRepository.findById(topFolderId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_TOP_FOLDER));

        return modelMapper.map(topFolder, FindFolderDTO.class);
    }

    public void updateFolder(Long folderId, UpdateFolderDTO updateFolderDTO) {

        Folder folder = folderRepository.findById(folderId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_FOLDER));

        Folder topFolder = folderRepository.findById(updateFolderDTO.getTopFolderId())
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_TOP_FOLDER));

        boolean IsExistsSameFolderName;


        // 부모 폴더가 없다면 -> 해당 폴더가 가장 상위 폴더
        if (topFolder == null) {

            // 가장 상위 폴더에서 같은 이름이 있는지 확인
            IsExistsSameFolderName = folderRepository
                    .existsByFolderNameAndTeamspaceIdAndPublishStatusAndTopFolderIdIsNull(
                            folder.getFolderName(),
                            folder.getTeamspaceId(),
                            StatusType.PUBLISHED
                    );
        } else { // 부모 폴더가 있다면

            // 수정하려는 위치가 삭제된 폴더라면 오류 던짐
            if (topFolder.getPublishStatus().equals(StatusType.DELETED)) {
                throw new CustomException(ErrorCode.TOP_FOLDER_IS_DELETED);
            }

            // 해당 부모 폴더의 바로 하단 폴더에서 같은 이름의 폴더가 있는지 확인
            IsExistsSameFolderName = folderRepository
                    .existsByFolderNameAndPublishStatusAndTopFolderId(
                            folder.getFolderName(),
                            StatusType.PUBLISHED,
                            topFolder);
        }

        // 같은 이름의 폴더가 존재하면 오류 던짐
        if (IsExistsSameFolderName) {
            throw new CustomException(ErrorCode.FOLDER_SAME_NAME);
        }

        folder.modifyFolder(topFolder, updateFolderDTO.getFolderName());
    }

    public void deleteFolder(Long folderId) {

        try {
            folderRepository.deleteById(folderId);
        } catch (Exception e) {
            throw new CustomException(ErrorCode.FOLDER_DELETE_FAIL);
        }
    }
}
