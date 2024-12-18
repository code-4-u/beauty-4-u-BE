package com.beauty4u.backend.teamspace.command.application.service;

import com.beauty4u.backend.teamspace.command.application.dto.teamboard.TeamBoardReplyReqDTO;
import com.beauty4u.backend.teamspace.command.domain.service.TeamBoardReplyDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TeamBoardReplyService {

    private final TeamBoardReplyDomainService teamBoardReplyDomainService;

    @Transactional
    public void saveTeamBoardReply(String loginUserCode, Long teamBoardId, TeamBoardReplyReqDTO teamBoardReplyReqDTO) {

        teamBoardReplyDomainService.saveTeamBoardReply(loginUserCode, teamBoardId, teamBoardReplyReqDTO);
    }
}
