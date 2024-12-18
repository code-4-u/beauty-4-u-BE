package com.beauty4u.backend.teamspace.query.dto.teamboard;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TeamBoardDetailResDTO {

    TeamBoardDetailDTO teamBoardDetailDTO;
    List<TeamBoardReplyListDTO> teamBoardReplyList;
}
