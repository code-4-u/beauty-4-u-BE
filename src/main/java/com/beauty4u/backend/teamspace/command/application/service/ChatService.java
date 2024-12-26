package com.beauty4u.backend.teamspace.command.application.service;

import com.beauty4u.backend.file.command.application.dto.FileDTO;
import com.beauty4u.backend.file.command.application.dto.FileSaveReqDTO;
import com.beauty4u.backend.file.command.application.service.FileService;
import com.beauty4u.backend.teamspace.command.application.dto.chat.ChatMessageReqDto;
import com.beauty4u.backend.teamspace.command.domain.aggregate.ChatMessage;
import com.beauty4u.backend.teamspace.command.domain.repository.ChatMessageRepository;
import com.beauty4u.backend.teamspace.command.domain.service.ChatMessageDomainService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatService {

    private final ChatMessageRepository chatMessageRepository;
    private final ChatMessageDomainService chatMessageDomainService;
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final FileService fileService;

    public void sendMessage(Long chatRoomId, ChatMessageReqDto chatMessageReqDto) {

        // 파일 업로드
        List<Long> fileIds = new ArrayList<>();
        List<String> fileUrls = new ArrayList<>();

        // 파일 업로드는 좀 고민해보기

        if (chatMessageReqDto.getAttachedFiles() != null) {
            for (FileDTO fileDTO : chatMessageReqDto.getAttachedFiles()) {
                fileUrls.add(fileDTO.getFileUrl());
            }
        }

        // 2. 파일 저장
        if (!fileUrls.isEmpty()) {
            FileSaveReqDTO fileSaveReqDTO = new FileSaveReqDTO(chatRoomId, fileUrls, "chat");
            fileIds =  fileService.saveImages(fileSaveReqDTO); // 파일 저장 로직 위임
        }

        // 메시지 생성 및 저장
        ChatMessage chatMessage = ChatMessage.builder()
                .messageContent(chatMessageReqDto.getMessageContent())
                .userCode(chatMessageReqDto.getUserCode())
                .chatRoomId(chatRoomId)
                .attachedFileIds(fileIds)
                .build();

        // 비동기로 메시지 저장
        saveMessageAsync(chatMessage);

        // STOMP 메시지 전송
        // 해당 주소를 수신하고 있는 유저들에게 보내짐
        simpMessagingTemplate.convertAndSend("/sub/chat/" + chatRoomId, chatMessageReqDto);
        log.info("Message [{}] saved and sent to destination [{}]", chatMessage.getMessageContent(), "/sub/chat/" + chatRoomId);
    }

    @Async
    public void saveMessageAsync(ChatMessage chatMessage) {
        chatMessageRepository.save(chatMessage);
        log.info("Message [{}] saved to database", chatMessage.getMessageContent());
    }

    public void deleteByChatRoomId(Long chatRoomId) {
        chatMessageDomainService.deleteByChatRoomId(chatRoomId);
    }
}
