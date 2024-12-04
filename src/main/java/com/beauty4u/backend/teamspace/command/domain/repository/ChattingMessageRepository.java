package com.beauty4u.backend.teamspace.command.domain.repository;

import com.beauty4u.backend.teamspace.command.domain.aggregate.ChattingMessage;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChattingMessageRepository extends MongoRepository<ChattingMessage, Long> {
    ChattingMessage findByTeamspaceId(Long messageId);
}
