package com.beauty4u.backend.file.command.domain.service;

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

    public List<Long> saveFile(List<String> images, Long entityId, String entityType) {

        List<FileInfo> fileInfos = new ArrayList<>();

        if (entityType.equals("inform")) {

            String url = "/inform/" + entityId.toString();

            for (String image : images) {
                FileDTO fileDTO = new FileDTO();
                fileDTO.setFileS3Url(image);
                fileDTO.setFileType(FileType.INFORM);
                fileDTO.setFileUrl(url);
                fileInfos.add(modelMapper.map(fileDTO, FileInfo.class));
            }
        } else if (entityType.equals("inquiry")){

            String url = "/inquiry/" + entityId.toString();

            for (String image : images) {
                FileDTO fileDTO = new FileDTO();
                fileDTO.setFileS3Url(image);
                fileDTO.setFileType(FileType.INQUIRY);
                fileDTO.setFileUrl(url);
                fileInfos.add(modelMapper.map(fileDTO, FileInfo.class));
            }
        } else if (entityType.equals("teamboard")){

            String url = "/teamboard/" + entityId.toString();

            for (String image : images) {
                FileDTO fileDTO = new FileDTO();
                fileDTO.setFileS3Url(image);
                fileDTO.setFileType(FileType.TEAMBOARD);
                fileDTO.setFileUrl(url);
                fileInfos.add(modelMapper.map(fileDTO, FileInfo.class));
            }
        } else {

            String url = "/chat/" + entityId.toString();

            for (String image : images) {
                FileDTO fileDTO = new FileDTO();
                fileDTO.setFileS3Url(image);
                fileDTO.setFileType(FileType.CHAT);
                fileDTO.setFileUrl(url);
                fileInfos.add(modelMapper.map(fileDTO, FileInfo.class));
            }

        }

        // 저장된 엔티티 리스트 반환
        List<FileInfo> savedFiles = fileRepository.saveAll(fileInfos);

        // 저장된 ID 리스트 반환
        return savedFiles.stream()
                .map(FileInfo::getId) // 각 파일의 ID 추출
                .collect(Collectors.toList());
    }

    public void deleteFile(Long entityId, boolean isInformImage) {

        List<FileDTO> fileDTOS = new ArrayList<>();

        if (isInformImage) {
            InformDTO informDTO = informDomainService.findInform(entityId);

            Inform inform = modelMapper.map(informDTO, Inform.class);

//            fileRepository.deleteByInform(inform);
        } else {

            InquiryDTO inquiryDTO = inquiryDomainService.findInquiry(entityId);

            Inquiry inquiry = modelMapper.map(inquiryDTO, Inquiry.class);

//            fileRepository.deleteByInquiry(inquiry);
        }
    }

    public String s3UploadImage(MultipartFile image) {

        return s3ImageUtil.upload(image);
    }

    public void s3UploadDeleteImage(String image) {

        s3ImageUtil.deleteImageFromS3(image);
    }
}
