package com.beauty4u.backend.file.command.application.service;

import com.beauty4u.backend.file.command.application.dto.FileDeleteReqDTO;
import com.beauty4u.backend.file.command.application.dto.FileSaveReqDTO;
import com.beauty4u.backend.file.command.domain.service.FileDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FileService {

    private final FileDomainService fileDomainService;

    @Transactional
    public List<Long> saveImages(FileSaveReqDTO fileSaveReqDTO) {

        return fileDomainService.saveFile(
                fileSaveReqDTO.getFileUrls(),
                fileSaveReqDTO.getImageS3Urls(),
                fileSaveReqDTO.getEntityType());
    }

    @Transactional
    public void deleteImages(FileDeleteReqDTO fileDeleteReqDTO) {

        fileDomainService.deleteFile(
                fileDeleteReqDTO.getFileIdList(),
                fileDeleteReqDTO.getFileS3UrlList()
        );
    }

    @Transactional
    public String uploadImage(MultipartFile image) {

        return fileDomainService.s3UploadImage(image);
    }

    @Transactional
    public void uploadDeleteImage(List<String> imageUrls) {

        for (String image : imageUrls) {
            fileDomainService.s3UploadDeleteImage(image);
        }
    }
}
