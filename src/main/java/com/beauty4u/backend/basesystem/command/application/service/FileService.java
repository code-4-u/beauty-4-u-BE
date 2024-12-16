package com.beauty4u.backend.basesystem.command.application.service;

import com.beauty4u.backend.basesystem.command.application.dto.FileDeleteReqDTO;
import com.beauty4u.backend.basesystem.command.application.dto.FileSaveReqDTO;
import com.beauty4u.backend.basesystem.command.domain.service.FileDomainService;
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
    public void saveImages(FileSaveReqDTO fileSaveReqDTO) {
        fileDomainService.saveFile(
                fileSaveReqDTO.getImageUrls(),
                fileSaveReqDTO.getEntityId(),
                fileSaveReqDTO.getIsInform());
    }

    public void deleteImages(FileDeleteReqDTO fileDeleteReqDTO) {
        fileDomainService.deleteFile(fileDeleteReqDTO.getEntityId(), fileDeleteReqDTO.getIsInform());
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
