package com.beauty4u.backend.teamspace.command.application.controller;

import com.beauty4u.backend.teamspace.command.application.dto.chat.ChatMessageReqDto;
import com.beauty4u.backend.teamspace.command.application.service.ChatService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class ChatCommandController {

    private final ChatService chatService;

    // 웹소켓 메세지를 특정 경로로 매핑한다.
    @MessageMapping("/{teamspaceId}") // /pub/1
    public void sendMessage(@DestinationVariable(value = "teamspaceId") Long teamspaceId,
                            ChatMessageReqDto chatMessageReqDto)
                            throws JsonProcessingException {
        chatService.sendMessage(teamspaceId, chatMessageReqDto);
    }
}
