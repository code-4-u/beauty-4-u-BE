package com.beauty4u.backend.inform.query.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InformFilterDTO {

    private LocalDate startDate;
    private LocalDate endDate;
    private String informTitle;
    private String publishStatus;
    private String sort;
    private String order;
    private Long page;
    private Long count;
}
