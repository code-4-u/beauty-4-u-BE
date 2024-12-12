package com.beauty4u.backend.teamspace.command.application.service;

import com.beauty4u.backend.teamspace.command.domain.aggregate.Teamspace;
import com.beauty4u.backend.teamspace.command.domain.repository.TeamSpaceRepository;
import com.beauty4u.backend.user.command.domain.aggregate.Dept;
import com.beauty4u.backend.user.command.domain.repository.DeptRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeamSpaceService {

    private final TeamSpaceRepository teamSpaceRepository;
    private final DeptRepository deptRepository;

    // 팀스페이스 생성(Admin)
    @Transactional
    public Teamspace createTeamspace(String deptCode) {

        Dept dept = deptRepository.findById(deptCode)
                .orElseThrow(() -> new EntityNotFoundException("해당 부서 코드가 존재하지 않습니다."));

        Teamspace teamspace = new Teamspace(null, dept.getDeptCode());

        return teamSpaceRepository.save(teamspace);
    }

    // 팀스페이스 삭제
    @Transactional
    public void deleteTeamspace(Long teamSpaceId) {
        Teamspace teamspace = teamSpaceRepository.findById(teamSpaceId)
                .orElseThrow(() -> new EntityNotFoundException("id에 해당하는 팀스페이스가 없습니다."));
        teamSpaceRepository.delete(teamspace);
    }
}
