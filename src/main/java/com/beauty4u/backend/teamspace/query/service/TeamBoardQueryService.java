package com.beauty4u.backend.teamspace.query.service;

import com.beauty4u.backend.teamspace.query.dto.teamboard.TeamBoardFilterDTO;
import com.beauty4u.backend.teamspace.query.dto.teamboard.TeamBoardListDTO;
import com.beauty4u.backend.teamspace.query.dto.teamboard.TeamBoardListResDTO;
import com.beauty4u.backend.teamspace.query.mapper.TeamBoardQueryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamBoardQueryService {

    private final TeamBoardQueryMapper teamBoardQueryMapper;

    @Transactional(readOnly = true)
    public TeamBoardListResDTO findTeamBoardList(TeamBoardFilterDTO teamBoardFilterDTO) {

        Long offset = (teamBoardFilterDTO.getPage() - 1) * teamBoardFilterDTO.getCount();

        LocalDateTime startDateTime = null;
        LocalDateTime endDateTime = null;

        if (teamBoardFilterDTO.getStartDate() != null) {
            startDateTime = teamBoardFilterDTO.getStartDate().atStartOfDay();
        }

        if (teamBoardFilterDTO.getEndDate() != null) {
            endDateTime = teamBoardFilterDTO.getEndDate().atStartOfDay();
        }

        List<TeamBoardListDTO> teamBoardList = teamBoardQueryMapper.findTeamBoardList(
                teamBoardFilterDTO.getTeamBoardTitle(),
                teamBoardFilterDTO.getPublishStatus(),
                startDateTime,
                endDateTime,
                teamBoardFilterDTO.getSort(),
                teamBoardFilterDTO.getOrder(),
                offset,
                teamBoardFilterDTO.getCount());

        TeamBoardListResDTO teamBoardListResDTO = new TeamBoardListResDTO();
        teamBoardListResDTO.setTeamBoardList(teamBoardList);

        Long totalCount = teamBoardQueryMapper.findTeamBoardListTotalCount(
                teamBoardFilterDTO.getTeamBoardTitle(),
                teamBoardFilterDTO.getPublishStatus(),
                startDateTime,
                endDateTime,
                teamBoardFilterDTO.getSort(),
                teamBoardFilterDTO.getOrder()
        );
        teamBoardListResDTO.setTotalCount(totalCount);

        return teamBoardListResDTO;
    }
}
