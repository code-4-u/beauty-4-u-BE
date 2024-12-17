package com.beauty4u.backend.teamspace.query.service;

import com.beauty4u.backend.teamspace.command.domain.aggregate.ChatMessage;
import com.beauty4u.backend.teamspace.command.domain.repository.ChatMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatQueryService {

    private final ChatMessageRepository chatMessageRepository;

    public List<ChatMessage> getChatHistory(Long teamspaceId) {

        List<ChatMessage> chatMessages = chatMessageRepository.findByTeamspaceId(teamspaceId);
        return chatMessages;
    }
}
