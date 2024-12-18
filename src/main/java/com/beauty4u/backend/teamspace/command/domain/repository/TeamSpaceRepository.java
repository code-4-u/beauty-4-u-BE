package com.beauty4u.backend.teamspace.command.domain.repository;

import com.beauty4u.backend.teamspace.command.domain.aggregate.Teamspace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeamSpaceRepository extends JpaRepository<Teamspace, Long> {

    // 특정 부서코드 ID를 기준으로 팀스페이스 조회
    Optional<Teamspace> findTeamspaceByDeptCode(String deptCode);

    // 해당 부서가 존재하는지 여부 확인
    boolean existsByDeptCode(String deptCode);
}
