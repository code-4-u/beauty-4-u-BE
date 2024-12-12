package com.beauty4u.backend.teamspace.command.application.service;

import com.beauty4u.backend.teamspace.command.application.dto.folder.CreateFolderReqDto;
import com.beauty4u.backend.teamspace.command.domain.aggregate.Folder;
import com.beauty4u.backend.teamspace.command.domain.aggregate.Teamspace;
import com.beauty4u.backend.teamspace.command.domain.repository.FolderRepository;
import com.beauty4u.backend.teamspace.command.domain.repository.TeamSpaceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.elasticsearch.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class FolderService {

    private final TeamSpaceRepository teamSpaceRepository;
    private final FolderRepository folderRepository;


    public void createFolder(Long teamspaceId, CreateFolderReqDto request) {
        Teamspace teamspace = teamSpaceRepository.findById(teamspaceId)
                .orElseThrow(() -> new ResourceNotFoundException("Teamspace not found"));
        // 부모 폴더 조회
        Folder topFolder = request.getTopFolderId() != null ?
                folderRepository.findById(request.getTopFolderId()).orElse(null) : null;

        // Folder 객체 생성
        Folder folder = Folder.builder()
                .teamspaceId(teamspace)                 // 팀스페이스 설정
                .topFolderId(topFolder)           // 부모 폴더 설정
                .folderName(request.getFolderName())  // 폴더 이름 설정
                .folderCreatedDate(ZonedDateTime.now())       // 생성일 설정
                .build();

        // 폴더 저장
        folderRepository.save(folder);
    }
}
