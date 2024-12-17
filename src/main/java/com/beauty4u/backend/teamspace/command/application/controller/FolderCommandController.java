package com.beauty4u.backend.teamspace.command.application.controller;

import com.beauty4u.backend.common.response.ApiResponse;
import com.beauty4u.backend.common.response.ResponseUtil;
import com.beauty4u.backend.common.success.SuccessCode;
import com.beauty4u.backend.teamspace.command.application.dto.folder.UpdateFolderDTO;
import com.beauty4u.backend.teamspace.command.application.dto.folder.saveFolderDTO;
import com.beauty4u.backend.teamspace.command.application.service.FolderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/folder")
@RequiredArgsConstructor
@Tag(name = "TeamSpaceFolder", description = "팀스페이스 폴더 관련 API")
public class FolderCommandController {

    private final FolderService folderService;

    @Operation(summary = "팀스페이스 폴더 생성", description = "팀스페이스에 새 폴더를 생성한다.")
    @PostMapping
    public ResponseEntity<ApiResponse<Void>> saveFolder(
            @RequestBody saveFolderDTO saveFolderDTO
    ) {

        folderService.saveFolder(saveFolderDTO);

        return ResponseUtil.successResponse(SuccessCode.FOLDER_SAVE_SUCCESS);
    }

    @Operation(summary = "폴더 수정", description = "폴더를 수정한다.")
    @PutMapping("/{folderId}")
    public ResponseEntity<ApiResponse<Void>> updateFolder(
            @PathVariable Long folderId,
            @RequestBody UpdateFolderDTO updateFolderDTO
    ) {

        folderService.updateFolder(folderId, updateFolderDTO);

        return ResponseUtil.successResponse(SuccessCode.FOLDER_UPDATE_SUCCESS);
    }

    @Operation(summary = "폴더 삭제", description = "폴더를 삭제한다.")
    @DeleteMapping("/{folderId}")
    public ResponseEntity<ApiResponse<Void>> deleteFolder(
            @PathVariable Long folderId
    ) {

        folderService.deleteFolder(folderId);

        return ResponseUtil.successResponse(SuccessCode.FOLDER_DELETE_SUCCESS);
    }
}
