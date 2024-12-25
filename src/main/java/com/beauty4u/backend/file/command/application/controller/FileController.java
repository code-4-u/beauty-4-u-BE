package com.beauty4u.backend.file.command.application.controller;

import com.beauty4u.backend.common.response.ApiResponse;
import com.beauty4u.backend.common.response.ResponseUtil;
import com.beauty4u.backend.common.success.SuccessCode;
import com.beauty4u.backend.file.command.application.dto.FileDeleteReqDTO;
import com.beauty4u.backend.file.command.application.dto.FileSaveReqDTO;
import com.beauty4u.backend.file.command.application.service.FileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/file")
@Tag(name = "File", description = "파일 관련 API")
public class FileController {

    private final FileService fileService;

    // db에 저장
    @Operation(summary = "업로드된 사진 저장", description = "업로드 된 사진 저장")
    @PostMapping("/save")
    public ResponseEntity<ApiResponse<Void>> saveImage(
            @RequestBody FileSaveReqDTO fileSaveReqDTO
    ) {

        fileService.saveImages(fileSaveReqDTO);

        return ResponseUtil.successResponse(SuccessCode.FILE_SAVE_SUCCESS);
    }

    // db에서 삭제
    @Operation(summary = "업로드 된 사진 삭제", description = "업로드 된 사진 삭제")
    @PostMapping("/delete")
    public ResponseEntity<ApiResponse<Void>> deleteImage(
            @RequestBody FileDeleteReqDTO fileDeleteReqDTO
    ) {

        fileService.deleteImages(fileDeleteReqDTO);

        return ResponseUtil.successResponse(SuccessCode.FILE_DELETE_SUCCESS);
    }

    // s3에 저장
    @Operation(summary = "사진 업로드", description = "사진을 업로드한다.")
    @PostMapping("/s3/upload")
    public ResponseEntity<ApiResponse<String>> s3UploadImage(
            @RequestPart(value = "image", required = false) MultipartFile image
    ) {

        System.out.println(image);

        String profileImage = fileService.uploadImage(image);

        return ResponseUtil.successResponse(SuccessCode.UPLOAD_IMAGE_SUCCESS, profileImage);
    }

    // s3에서 삭제
    @Operation(summary = "사진 업로드 삭제", description = "업로드 된 사진을 삭제한다.")
    @PostMapping("/s3/uploadList")
    public ResponseEntity<ApiResponse<String>> s3UploadDeleteImage(
            @RequestPart(value = "image", required = false) List<String> imageUrls
    ) {

        fileService.uploadDeleteImage(imageUrls);

        return ResponseUtil.successResponse(SuccessCode.UPLOAD_IMAGE_SUCCESS);
    }
}
