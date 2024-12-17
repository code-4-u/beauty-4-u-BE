package com.beauty4u.backend.teamspace.query.controller;

import com.beauty4u.backend.common.response.ApiResponse;
import com.beauty4u.backend.common.response.ResponseUtil;
import com.beauty4u.backend.common.success.SuccessCode;
import com.beauty4u.backend.teamspace.query.dto.folder.FindFolderListFilterDTO;
import com.beauty4u.backend.teamspace.query.dto.folder.FolderListResDTO;
import com.beauty4u.backend.teamspace.query.service.FolderQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/folder")
@Tag(name = "TeamSpaceFolder", description = "팀스페이스 폴더 관련 API")
public class FolderQueryController {

    private final FolderQueryService folderQueryService;

    @Operation(summary = "폴더 목록 조회", description = "폴더 목록을 조회한다.")
    @GetMapping
    public ResponseEntity<ApiResponse<List<FolderListResDTO>>> findFolderList(
            @ModelAttribute FindFolderListFilterDTO findFolderListFilterDTO
    ) {

        List<FolderListResDTO> folderListResDTOS = folderQueryService.findFolderList(findFolderListFilterDTO);

        return ResponseUtil.successResponse(SuccessCode.FOLDER_LIST_FIND_SUCCESS, folderListResDTOS);
    }
}
