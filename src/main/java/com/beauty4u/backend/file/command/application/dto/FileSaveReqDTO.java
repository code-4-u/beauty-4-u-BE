package com.beauty4u.backend.file.command.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FileSaveReqDTO {

    private List<String> imageS3Urls; // s3 urls
    private List<String> fileUrls; // 원래 파일 이름
    private String entityType; // 타입 지정
}
