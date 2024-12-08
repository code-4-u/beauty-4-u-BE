package com.beauty4u.backend.inform.query.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class InformListResDTO {

    private Long informId;
    private String userCode;
    private String informTitle;
    private String informStatus;
    private Long informViewcount;
    private LocalDateTime informCreatedDate;
}
