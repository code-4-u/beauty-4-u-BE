package com.beauty4u.backend.teamspace.command.domain.service;

import com.beauty4u.backend.common.exception.CustomException;
import com.beauty4u.backend.common.exception.ErrorCode;
import com.beauty4u.backend.teamspace.command.application.dto.teamboard.TeamBoardReqDTO;
import com.beauty4u.backend.teamspace.command.domain.aggregate.TeamBoard;
import com.beauty4u.backend.teamspace.command.domain.repository.TeamBoardRepository;
import com.beauty4u.backend.user.command.domain.aggregate.UserInfo;
import com.beauty4u.backend.user.command.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeamBoardDomainService {

    private final TeamBoardRepository teamBoardRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public Long saveTeamBoard(String loginUserCode, TeamBoardReqDTO teamBoardReqDTO) {

        TeamBoard teamBoard = modelMapper.map(teamBoardReqDTO, TeamBoard.class);

        UserInfo user = userRepository.findByUserCode(loginUserCode)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER));

        teamBoard.modifyUser(user);

        try {
            teamBoardRepository.save(teamBoard);
        } catch (Exception e) {
            throw new CustomException(ErrorCode.NOT_SAVED_TEAMBOARD);
        }

        return teamBoard.getId();
    }

    public void updateTeamBoard(Long teamBoardId, TeamBoardReqDTO teamBoardReqDTO) {

        String title = teamBoardReqDTO.getTeamBoardTitle();
        String content = teamBoardReqDTO.getTeamBoardContent();

        TeamBoard teamBoard = teamBoardRepository.findById(teamBoardId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_TEAMBOARD));

        teamBoard.modifyTeamBoard(title, content);
    }

    public void deleteTeamBoard(Long teamBoardId) {

        TeamBoard teamBoard = teamBoardRepository.findById(teamBoardId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_TEAMBOARD));

        teamBoardRepository.delete(teamBoard);
    }
}
