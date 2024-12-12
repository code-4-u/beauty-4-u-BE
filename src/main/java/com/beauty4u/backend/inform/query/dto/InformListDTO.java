package com.beauty4u.backend.inform.query.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InformListDTO {

    private Long informId;
    private String userCode;
    private String userName;
    private String informTitle;
    private String publishStatus;
    private Long informViewcount;
    private LocalDateTime createdDate;
}
