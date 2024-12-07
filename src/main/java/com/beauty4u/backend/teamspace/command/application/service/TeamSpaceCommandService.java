package com.beauty4u.backend.teamspace.command.application.service;

import com.beauty4u.backend.teamspace.command.domain.aggregate.Teamspace;
import com.beauty4u.backend.teamspace.command.domain.repository.TeamSpaceRepository;
import com.beauty4u.backend.user.command.domain.repository.DeptRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeamSpaceCommandService {

    private final TeamSpaceRepository teamSpaceRepository;
    private final DeptRepository deptRepository;

    // 새로운 팀스페이스 생성
    @Transactional
    public void createTeamspace(String deptCode) {

//        Dept dept = deptRepository.findByDeptCode(deptCode)
//                .orElseThrow(() -> new EntityNotFoundException("Department not found with code: " + deptCode));

        Long teamspaceId = System.currentTimeMillis();
        Teamspace teamspace = Teamspace.builder()
//                .teamspaceId(teamspaceId) // 엔티티에서 DB에 기본키 권한설정 (@GeneratedValue을 주었음)
                .deptCode(deptCode)  // Dept 엔티티를 설정
                .build();

        teamSpaceRepository.save(teamspace);
    }
}
