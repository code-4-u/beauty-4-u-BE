package com.beauty4u.backend.teamspace.command.application.service;

import com.beauty4u.backend.common.aggregate.StatusType;
import com.beauty4u.backend.common.exception.CustomException;
import com.beauty4u.backend.common.exception.ErrorCode;
import com.beauty4u.backend.teamspace.command.application.dto.folder.FindFolderDTO;
import com.beauty4u.backend.teamspace.command.application.dto.folder.UpdateFolderDTO;
import com.beauty4u.backend.teamspace.command.application.dto.folder.saveFolderDTO;
import com.beauty4u.backend.teamspace.command.application.dto.chatroom.FindChatRoomDTO;
import com.beauty4u.backend.teamspace.command.domain.service.FolderDomainService;
import com.beauty4u.backend.teamspace.command.domain.service.ChatRoomDomainService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class FolderService {

    private final ChatRoomDomainService chatRoomDomainService;
    private final FolderDomainService folderDomainService;

    @Transactional
    public void saveFolder(saveFolderDTO saveFolderDTO) {

        FindChatRoomDTO findChatRoomDTO = chatRoomDomainService.findChatRoomById(saveFolderDTO.getTeamspaceId());

        // 부모 폴더 조회
        FindFolderDTO topFolderDTO = saveFolderDTO.getTopFolderId() != null ?
                folderDomainService.findById(saveFolderDTO.getTopFolderId()) : null;

        // 부모 폴더가 삭제된 상태라면 오류 던짐
        if (topFolderDTO != null) {
            if (topFolderDTO.getPublishStatus().equals(StatusType.DELETED)) {
                throw new CustomException(ErrorCode.TOP_FOLDER_IS_DELETED);
            }
        }

        // 폴더 저장
        folderDomainService.saveFolder(saveFolderDTO.getFolderName(), topFolderDTO, findChatRoomDTO);
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

    @Transactional
    public void deleteFolder(Long folderId) {

        folderDomainService.deleteFolder(folderId);
    }
}
