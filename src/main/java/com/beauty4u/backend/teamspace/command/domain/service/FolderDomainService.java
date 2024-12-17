package com.beauty4u.backend.teamspace.command.domain.service;

import com.beauty4u.backend.common.exception.CustomException;
import com.beauty4u.backend.common.exception.ErrorCode;
import com.beauty4u.backend.teamspace.command.application.dto.folder.FindFolderDTO;
import com.beauty4u.backend.teamspace.command.domain.aggregate.Folder;
import com.beauty4u.backend.teamspace.command.domain.repository.FolderRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FolderDomainService {

    private final FolderRepository folderRepository;
    private final ModelMapper modelMapper;

    public void saveFolder(Folder folder) {

        try {
            folderRepository.save(folder);
        } catch (Exception e) {
            throw new CustomException(ErrorCode.FOLDER_SAVE_FAIL);
        }
    }

    public FindFolderDTO findById(Long topFolderId) {

        Folder topFolder = folderRepository.findById(topFolderId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_TOP_FOLDER));

        return modelMapper.map(topFolder, FindFolderDTO.class);
    }
}
