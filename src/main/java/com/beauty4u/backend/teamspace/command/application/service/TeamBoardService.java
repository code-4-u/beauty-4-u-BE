package com.beauty4u.backend.teamspace.command.application.service;

import com.beauty4u.backend.teamspace.command.application.dto.teamboard.TeamBoardReqDTO;
import com.beauty4u.backend.teamspace.command.domain.service.TeamBoardDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TeamBoardService {

    private final TeamBoardDomainService teamBoardDomainService;

    @Transactional
    public Long saveTeamBoard(String loginUserCode, TeamBoardReqDTO teamBoardReqDTO) {

        return teamBoardDomainService.saveTeamBoard(loginUserCode, teamBoardReqDTO);
    }
}
