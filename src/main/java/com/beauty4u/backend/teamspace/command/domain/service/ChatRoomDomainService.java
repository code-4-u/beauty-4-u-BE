package com.beauty4u.backend.teamspace.command.domain.service;

import com.beauty4u.backend.common.exception.CustomException;
import com.beauty4u.backend.common.exception.ErrorCode;
import com.beauty4u.backend.teamspace.command.application.dto.chat.ChatMessageReqDto;
import com.beauty4u.backend.teamspace.command.application.dto.chatroom.FindChatRoomDTO;
import com.beauty4u.backend.teamspace.command.domain.aggregate.ChatMessage;
import com.beauty4u.backend.teamspace.command.domain.aggregate.ChatRoom;
import com.beauty4u.backend.teamspace.command.domain.repository.ChatMessageRepository;
import com.beauty4u.backend.teamspace.command.domain.repository.ChatRoomRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatRoomDomainService {

    private final ChatRoomRepository chatRoomRepository;
    private final ChatMessageRepository chatMessageRepository;
    private final ModelMapper modelMapper;

    public FindChatRoomDTO findChatRoomById(Long chatRoomId) {

        ChatRoom chatRoom = chatRoomRepository.findById(chatRoomId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_CHATROOM));

        return modelMapper.map(chatRoom, FindChatRoomDTO.class);
    }

    public ChatMessageReqDto findMessage(Long messageId) {

        ChatMessage chatMessage = chatMessageRepository.findById(messageId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_MESSAGE));

        return modelMapper.map(chatMessage, ChatMessageReqDto.class);
    }

    @Transactional
    public void deleteChatRoom(Long chatRoomId) {
        chatRoomRepository.deleteById(chatRoomId);
    }
}
