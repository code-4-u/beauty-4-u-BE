package com.beauty4u.backend.teamspace.command.domain.repository;

import com.beauty4u.backend.teamspace.command.domain.aggregate.Teamspace;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface TeamSpaceRepository extends JpaRepository<Teamspace, Long> {
    Optional<Teamspace> findTeamspaceByDeptCode(String deptCode);
}
