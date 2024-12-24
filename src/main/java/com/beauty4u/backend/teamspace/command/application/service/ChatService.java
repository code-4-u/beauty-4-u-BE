package com.beauty4u.backend.teamspace.command.application.service;

import com.beauty4u.backend.teamspace.command.application.dto.chat.ChatMessageReqDto;
import com.beauty4u.backend.teamspace.command.domain.aggregate.ChatMessage;
import com.beauty4u.backend.teamspace.command.domain.repository.ChatMessageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatService {

    private final ChatMessageRepository chatMessageRepository;
    private final SimpMessagingTemplate simpMessagingTemplate;

    public void sendMessage(Long chatRoomId, ChatMessageReqDto chatMessageReqDto) {

        // 메시지 생성 및 저장
        ChatMessage chatMessage = ChatMessage.builder()
                .messageContent(chatMessageReqDto.getMessageContent())
                .userCode(chatMessageReqDto.getUserCode())
                .chatRoomId(chatRoomId)
                .build();

        // 비동기로 메시지 저장
        saveMessageAsync(chatMessage);

        // STOMP 메시지 전송
        // 해당 주소를 수신하고 있는 유저들에게 보내짐
        simpMessagingTemplate.convertAndSend("/sub/teamspace/" + chatRoomId, chatMessageReqDto);
        log.info("Message [{}] saved and sent to destination [{}]", chatMessage.getMessageContent(), "/sub/teamspace/" + chatRoomId);
    }

    @Async
    public void saveMessageAsync(ChatMessage chatMessage) {
        chatMessageRepository.save(chatMessage);
        log.info("Message [{}] saved to database", chatMessage.getMessageContent());
    }
}
