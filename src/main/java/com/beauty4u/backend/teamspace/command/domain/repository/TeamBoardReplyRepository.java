package com.beauty4u.backend.teamspace.command.domain.repository;

import com.beauty4u.backend.teamspace.command.domain.aggregate.TeamBoardReply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamBoardReplyRepository extends JpaRepository<TeamBoardReply, Long> {
}
