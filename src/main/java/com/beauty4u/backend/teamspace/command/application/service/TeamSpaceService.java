package com.beauty4u.backend.teamspace.command.application.service;

import com.beauty4u.backend.teamspace.command.domain.aggregate.Teamspace;
import com.beauty4u.backend.teamspace.command.domain.repository.TeamSpaceRepository;
import com.beauty4u.backend.user.command.domain.aggregate.Dept;
import com.beauty4u.backend.user.command.domain.repository.DeptRepository;
import com.beauty4u.backend.user.command.domain.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamSpaceService {

    private final TeamSpaceRepository teamSpaceRepository;
    private final DeptRepository deptRepository;

    // 팀스페이스 생성
    @Transactional
    public Teamspace createTeamspace(String deptCode) {

        Dept dept = deptRepository.findById(deptCode)
                .orElseThrow(() -> new EntityNotFoundException("해당 부서 코드가 존재하지 않습니다."));

        Teamspace teamspace = new Teamspace(null, dept.getDeptCode());

        return teamSpaceRepository.save(teamspace);
    }

    // 팀스페이스 부서별 초기 생성
    @Transactional
    public List<Teamspace> createAndAssignTeamSpaces() {

        List<String> departmentCodes = deptRepository.findAll().stream()
                .map(Dept::getDeptCode)
                .distinct()
                .toList();

        List<Teamspace> createdTeamspaces = new ArrayList<>();
        for (String deptCode : departmentCodes) {
            // 부서별 팀스페이스 존재 여부 확인
            boolean exists = teamSpaceRepository.existsByDeptCode(deptCode);

            if (!exists) {
                // 팀스페이스 생성
                Teamspace newTeamspace = createTeamspace(deptCode);

//                // 해당 부서 코드에 속한 유저들을 조회
//                Dept dept = deptRepository.findByDeptCode(deptCode)
//                        .orElseThrow(() -> new EntityNotFoundException("부서 코드가 존재하지 않습니다."));
//                // DeptCode 타입이 Dept 객체 타입
//                List<UserInfo> usersInDepartment = userRepository.findByDeptCode(dept);

                createdTeamspaces.add(newTeamspace);
            }
        }
        return createdTeamspaces;
    }

    // 팀스페이스 삭제
    @Transactional
    public void deleteTeamspace(Long teamSpaceId) {
        Teamspace teamspace = teamSpaceRepository.findById(teamSpaceId)
                .orElseThrow(() -> new EntityNotFoundException("id에 해당하는 팀스페이스가 없습니다."));
        teamSpaceRepository.delete(teamspace);
    }
}
