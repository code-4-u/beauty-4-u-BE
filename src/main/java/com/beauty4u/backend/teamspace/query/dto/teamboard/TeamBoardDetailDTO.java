package com.beauty4u.backend.teamspace.query.dto.teamboard;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TeamBoardDetailDTO {

    private Long teamBoardId;
    private String userCode;
    private String userName;
    private String teamBoardTitle;
    private String teamBoardContent;
    private String publishStatus;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
