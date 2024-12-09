package com.beauty4u.backend.teamspace.query.service;

import com.beauty4u.backend.teamspace.command.domain.aggregate.ChatMessage;
import com.beauty4u.backend.teamspace.command.domain.repository.ChatMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatQueryService {
    private final ChatMessageRepository chatMessageRepository;

    public ChatMessage getChattingMessageInfoByTeamspaceId(Long teamspaceId) {

        ChatMessage chattingInfo = chatMessageRepository.findByTeamspaceId(teamspaceId);
        return chattingInfo;

    }
}
