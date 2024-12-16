package com.beauty4u.backend.inform.query.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class InformDetailResDTO {

    private Long informId;
    private String userCode;
    private String userName;
    private String informTitle;
    private String informContent;
    private String publishStatus;
    private Long informViewcount;
    private String fileUrl;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
