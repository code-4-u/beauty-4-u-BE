package com.beauty4u.backend.teamspace.query.dto.teamboard;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TeamBoardFilterDTO {

    private LocalDate startDate;
    private LocalDate endDate;
    private String teamBoardTitle;
    private String publishStatus;
    private String sort;
    private String order;
    private Long page;
    private Long count;
}
