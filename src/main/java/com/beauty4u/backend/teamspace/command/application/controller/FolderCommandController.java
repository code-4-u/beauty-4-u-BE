package com.beauty4u.backend.teamspace.command.application.controller;

import com.beauty4u.backend.common.response.ApiResponse;
import com.beauty4u.backend.common.response.ResponseUtil;
import com.beauty4u.backend.common.success.SuccessCode;
import com.beauty4u.backend.teamspace.command.application.dto.folder.saveFolderDTO;
import com.beauty4u.backend.teamspace.command.application.service.FolderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/teamspace/folder")
@RequiredArgsConstructor
@Tag(name = "TeamSpaceFolder", description = "팀스페이스 폴더 관련 API")
public class FolderCommandController {

    private final FolderService folderService;

    @PostMapping
    @Operation(summary = "팀스페이스 폴더 생성", description = "팀스페이스에 새 폴더를 생성한다.")
    public ResponseEntity<ApiResponse<Void>> saveFolder(
            @RequestBody saveFolderDTO saveFolderDTO
    ) {

        folderService.saveFolder(saveFolderDTO);

        return ResponseUtil.successResponse(SuccessCode.FOLDER_SAVE_SUCCESS);
    }
}
