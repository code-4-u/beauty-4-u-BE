package com.beauty4u.backend.teamspace.query.service;

import com.beauty4u.backend.teamspace.command.domain.aggregate.Teamspace;
import com.beauty4u.backend.teamspace.command.domain.repository.TeamSpaceRepository;
import com.beauty4u.backend.teamspace.query.dto.TeamSpaceResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeamSpaceQueryService {

    private final TeamSpaceRepository teamSpaceRepository;
    private final ModelMapper modelMapper;

    // 특정 부서의 팀스페이스 조회
    public TeamSpaceResponse getTeamSpaceByDeptCode(String deptCode) {
        Teamspace teamspace = teamSpaceRepository.findTeamspaceByDeptCode(deptCode)
                .orElseThrow(() -> new EntityNotFoundException("Teamspace not found for department code: " + deptCode));

        return modelMapper.map(teamspace, TeamSpaceResponse.class);
    }
}
