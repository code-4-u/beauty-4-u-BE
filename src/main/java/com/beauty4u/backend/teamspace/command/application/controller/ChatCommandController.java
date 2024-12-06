package com.beauty4u.backend.teamspace.command.application.controller;

import com.beauty4u.backend.teamspace.command.application.dto.chat.ChatMessageRequest;
import com.beauty4u.backend.teamspace.command.application.service.ChatCommandService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Slf4j
@RequiredArgsConstructor
@Controller
@Tag(name = "ChatMessage", description = "채팅메세지 API")
// 채팅은 WebSocket의 STOMP 프로토콜을 사용함.
// https를 사용하지 않으므로 RESTAPI가 아님
// Swagge-API에 나타나지 않음
public class ChatCommandController {

    private final ChatCommandService chatCommandService;
    private final SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/teamspace/{teamspaceId}/message")
    public void message(@DestinationVariable final Long teamspaceId, ChatMessageRequest chatMessageRequest) {
        chatCommandService.save(teamspaceId, chatMessageRequest);
        log.info("Message [{}] sent by user [{}] to teamspace [{}]",
                chatMessageRequest.getMessageContent(),
                chatMessageRequest.getUserCode(),
                teamspaceId);
    }
}
