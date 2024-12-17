package com.beauty4u.backend.teamspace.command.domain.service;

import com.beauty4u.backend.common.exception.CustomException;
import com.beauty4u.backend.common.exception.ErrorCode;
import com.beauty4u.backend.teamspace.command.application.dto.teamspace.FindTeamspaceDTO;
import com.beauty4u.backend.teamspace.command.domain.aggregate.Teamspace;
import com.beauty4u.backend.teamspace.command.domain.repository.TeamSpaceRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeamspaceDomainService {

    private final TeamSpaceRepository teamSpaceRepository;
    private final ModelMapper modelMapper;

    public FindTeamspaceDTO findTeamspaceById(Long teamspaceId) {

        Teamspace teamspace = teamSpaceRepository.findById(teamspaceId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_TEAMSPACE));

        return modelMapper.map(teamspace, FindTeamspaceDTO.class);
    }
}
