package com.beauty4u.backend.file.command.domain.service;

import com.beauty4u.backend.common.exception.CustomException;
import com.beauty4u.backend.common.exception.ErrorCode;
import com.beauty4u.backend.common.util.S3ImageUtil;
import com.beauty4u.backend.file.command.application.dto.FileDTO;
import com.beauty4u.backend.file.command.domain.aggregate.FileInfo;
import com.beauty4u.backend.file.command.domain.aggregate.FileType;
import com.beauty4u.backend.file.command.domain.repository.FileRepository;
import com.beauty4u.backend.inform.command.application.dto.InformDTO;
import com.beauty4u.backend.inform.command.domain.aggregate.Inform;
import com.beauty4u.backend.inform.command.domain.service.InformDomainService;
import com.beauty4u.backend.inquiry.command.application.dto.InquiryDTO;
import com.beauty4u.backend.inquiry.command.domain.aggregate.Inquiry;
import com.beauty4u.backend.inquiry.command.domain.service.InquiryDomainService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FileDomainService {

    private final FileRepository fileRepository;
    private final InformDomainService informDomainService;
    private final InquiryDomainService inquiryDomainService;
    private final S3ImageUtil s3ImageUtil;
    private final ModelMapper modelMapper;

    // 파일 저장
    public List<Long> saveFile(List<String> fileUrls, List<String> imageS3Urls, String entityType) {

        try {

            List<FileInfo> fileInfos = new ArrayList<>();

            FileType fileType = FileType.valueOf(entityType);

            for (int i = 0; i < fileUrls.size(); i++) {
                FileDTO fileDTO = new FileDTO();
                fileDTO.setFileS3Url(imageS3Urls.get(i));
                fileDTO.setFileType(fileType);
                fileDTO.setFileUrl(fileUrls.get(i));
                fileInfos.add(modelMapper.map(fileDTO, FileInfo.class));
            }

            // 저장된 엔티티 리스트 반환
            List<FileInfo> savedFiles = fileRepository.saveAll(fileInfos);

            // 저장된 ID 리스트 반환
            return savedFiles.stream()
                    .map(FileInfo::getId) // 각 파일의 ID 추출
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new CustomException(ErrorCode.FILE_LIST_SAVE_FAIL);
        }

    }

    // 파일 삭제
    public void deleteFile(List<Long> fileIdList, List<String> fileS3UrlList) {

        try {

            fileRepository.deleteAllByFileS3UrlIn(fileS3UrlList);
        } catch (Exception e) {
            throw new CustomException(ErrorCode.FILE_LIST_DELETE_FAIL);
        }
    }

    public String s3UploadImage(MultipartFile image) {

        return s3ImageUtil.upload(image);
    }

    public void s3UploadDeleteImage(String image) {

        s3ImageUtil.deleteImageFromS3(image);
    }
}
