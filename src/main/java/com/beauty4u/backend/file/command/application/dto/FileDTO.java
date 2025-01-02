package com.beauty4u.backend.file.command.application.dto;

import com.beauty4u.backend.file.command.domain.aggregate.FileType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FileDTO {

    private String fileS3Url;
    private FileType fileType;
    private String fileUrl;
}
