package com.beauty4u.backend.file.query.controller;

import com.beauty4u.backend.common.response.ApiResponse;
import com.beauty4u.backend.common.response.ResponseUtil;
import com.beauty4u.backend.common.success.SuccessCode;
import com.beauty4u.backend.file.query.dto.FileListReqDTO;
import com.beauty4u.backend.file.query.dto.FileListResDTO;
import com.beauty4u.backend.file.query.service.FileQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/file")
@Tag(name = "File", description = "파일 관련 API")
public class FileQueryController {

    private final FileQueryService fileQueryService;

    // db에 저장된 이미지 조회
    @Operation(summary = "특정 게시물에 업로드된 파일 조회", description = "특정 게시물에 업로드된 파일 조회")
    @GetMapping("/list")
    public ResponseEntity<ApiResponse<FileListResDTO>> findFileList(FileListReqDTO fileListReqDTO) {

        FileListResDTO fileListResDTO = fileQueryService.findFileList(fileListReqDTO.getFileUrl());

        return ResponseUtil.successResponse(SuccessCode.FILE_LIST_FIND_SUCCESS, fileListResDTO);
    }
}
