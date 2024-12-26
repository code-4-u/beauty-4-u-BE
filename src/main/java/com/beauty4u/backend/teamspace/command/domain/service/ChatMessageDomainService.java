package com.beauty4u.backend.teamspace.command.domain.service;

import com.beauty4u.backend.teamspace.command.domain.repository.ChatMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatMessageDomainService {

    private final ChatMessageRepository chatMessageRepository;

    public void deleteByChatRoomId(Long chatRoomId) {
        chatMessageRepository.deleteByChatRoomId(chatRoomId);
    }
}
