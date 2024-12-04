package com.beauty4u.backend.teamspace.query.service;

import com.beauty4u.backend.teamspace.command.domain.aggregate.ChattingMessage;
import com.beauty4u.backend.teamspace.command.domain.repository.ChattingMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChattingMessageService {
    private final ChattingMessageRepository chattingMessageRepository;
    private final MongoTemplate mongoTemplate;

    public ChattingMessage getChattingMessageInfoByTeamspaceId(Long teamspaceId) {
        ChattingMessage chattingInfo = chattingMessageRepository.findByTeamspaceId(teamspaceId);

        return chattingInfo;
    }
}
