package com.beauty4u.backend.teamspace.command.domain.service;

import com.beauty4u.backend.common.exception.CustomException;
import com.beauty4u.backend.common.exception.ErrorCode;
import com.beauty4u.backend.teamspace.command.application.dto.teamboard.TeamBoardReplyReqDTO;
import com.beauty4u.backend.teamspace.command.domain.aggregate.TeamBoard;
import com.beauty4u.backend.teamspace.command.domain.aggregate.TeamBoardReply;
import com.beauty4u.backend.teamspace.command.domain.repository.TeamBoardReplyRepository;
import com.beauty4u.backend.teamspace.command.domain.repository.TeamBoardRepository;
import com.beauty4u.backend.user.command.domain.aggregate.UserInfo;
import com.beauty4u.backend.user.command.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeamBoardReplyDomainService {

    private final TeamBoardReplyRepository teamBoardReplyRepository;
    private final TeamBoardRepository teamBoardRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public void saveTeamBoardReply(String loginUserCode, Long teamBoardId, TeamBoardReplyReqDTO teamBoardReplyReqDTO) {

        TeamBoardReply teamBoardReply = modelMapper.map(teamBoardReplyReqDTO, TeamBoardReply.class);

        TeamBoard teamBoard = teamBoardRepository.findById(teamBoardId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_TEAMBOARD));

        UserInfo user = userRepository.findByUserCode(loginUserCode)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER));

        teamBoardReply.saveReply(teamBoard, user);

        try {
            teamBoardReplyRepository.save(teamBoardReply);
        } catch (Exception e) {
            throw new CustomException(ErrorCode.NOT_FOUND_TEAMBOARD_REPLY);
        }
    }

    public void updateTeamBoardReply(Long teamBoardId, Long teamBoardReplyId, TeamBoardReplyReqDTO teamBoardReplyReqDTO) {

        String content = teamBoardReplyReqDTO.getTeamBoardReplyContent();

        TeamBoardReply teamBoardReply = teamBoardReplyRepository.findById(teamBoardReplyId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_TEAMBOARD_REPLY));

        if (!teamBoardReply.getTeamBoard().getId().equals(teamBoardId)) {
            throw new CustomException(ErrorCode.INVALID_TEAMBOARD_REPLY_UPDATE);
        }

        teamBoardReply.modifyContent(content);
    }
}
