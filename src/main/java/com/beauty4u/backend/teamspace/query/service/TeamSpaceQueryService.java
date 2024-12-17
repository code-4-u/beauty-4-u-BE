package com.beauty4u.backend.teamspace.query.service;

import com.beauty4u.backend.teamspace.query.dto.teamspace.TeamSpaceUserInfoDto;
import com.beauty4u.backend.teamspace.query.mapper.TeamSpaceQueryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class TeamSpaceQueryService {

    private final TeamSpaceQueryMapper teamSpaceQueryMapper;

    // 부서 코드로 채팅참여자 정보 조회
    public List<TeamSpaceUserInfoDto> findAllTeamSpaceUser(String deptCode) {
        return teamSpaceQueryMapper.findAllTeamSpaceUser(deptCode);
    }

    // 팀스페이스 id로 부서 코드 조회
    public String getDeptCodeByTeamspaceId(String teamspaceId) {
        return teamSpaceQueryMapper.findTeamSpaceDeptCode(teamspaceId);
    }
}
