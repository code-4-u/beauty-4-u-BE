package com.beauty4u.backend.file.command.application.service;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.beauty4u.backend.file.command.application.dto.FileDeleteReqDTO;
import com.beauty4u.backend.file.command.application.dto.FileSaveReqDTO;
import com.beauty4u.backend.file.command.domain.service.FileDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.net.URL;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FileService {

    private final FileDomainService fileDomainService;
    private final AmazonS3 amazonS3;

    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;

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

    public String generatePresignedUrl(String objectKey) {
        // 만료 시간 설정 (3일 후 만료)
        Date expiration = new Date();
        long expTimeMillis = System.currentTimeMillis() + (3600 * 1000 * 24 * 3); // 3일 유효
        expiration.setTime(expTimeMillis);
        System.out.println("URL 만료 시간: " + expiration);

        // Presigned URL 요청 생성
        GeneratePresignedUrlRequest generatePresignedUrlRequest =
                new GeneratePresignedUrlRequest(bucketName, objectKey)
                        .withMethod(HttpMethod.GET) // GET 요청
                        .withExpiration(expiration);

//        // 헤더 설정: Content-Disposition 추가
//        // 프론트에서 이미지 미리보기가 안되서 비활성화
//        ResponseHeaderOverrides headerOverrides = new ResponseHeaderOverrides()
//                .withContentDisposition("attachment; filename=" + objectKey);
//        generatePresignedUrlRequest.setResponseHeaders(headerOverrides);

        // Presigned URL 생성
        URL url = amazonS3.generatePresignedUrl(generatePresignedUrlRequest);

        return url.toString();
    }
}
