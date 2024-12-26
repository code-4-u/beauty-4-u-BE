package com.beauty4u.backend.teamspace.query.service;

import com.beauty4u.backend.teamspace.command.domain.aggregate.ChatMessage;
import com.beauty4u.backend.teamspace.command.domain.repository.ChatMessageRepository;
import com.beauty4u.backend.teamspace.query.dto.chatMessage.ChatMessageResDto;
import com.beauty4u.backend.teamspace.query.dto.chatroom.ChatRoomDTO;
import com.beauty4u.backend.teamspace.query.dto.chatroom.ChatRoomDetailsDto;
import com.beauty4u.backend.teamspace.query.dto.chatroom.ChatRoomUserInfoDto;
import com.beauty4u.backend.teamspace.query.mapper.ChatRoomQueryMapper;
import com.beauty4u.backend.user.command.domain.aggregate.UserInfo;
import com.beauty4u.backend.user.command.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ChatRoomQueryService {

    private final ChatRoomQueryMapper chatRoomQueryMapper;
    private final UserRepository userRepository;
    private final ChatMessageRepository chatMessageRepository;

    // 채팅방 채팅참여자 정보 조회
    @Transactional(readOnly = true)
    public List<ChatRoomUserInfoDto> findAllChatRoomUser(Long chatRoomId, String loginUserCode) {

        // 해당 유저가 채팅방에 속한 유저인지 확인
        // 1. 유저가 채팅방에 참여 중인지 확인
        if (!chatRoomQueryMapper.isUserInChatRoom(chatRoomId, loginUserCode)) {
            throw new IllegalArgumentException("Access denied: 채팅방 유저가 아닙니다.");
        }

        return chatRoomQueryMapper.findAllChatRoomUser(chatRoomId);
    }

    // 채팅방 세부 정보 조회
    @Transactional(readOnly = true)
    public ChatRoomDetailsDto getChatRoomDetails(Long chatRoomId,String loginUserCode) {

        // 1. 채팅 참여자 정보 조회
        List<ChatRoomUserInfoDto> participants = findAllChatRoomUser(chatRoomId, loginUserCode);

        // 3. 채팅 메시지와 사용자 이름 매핑
        List<ChatMessageResDto> messages = getChatMessagesWithUserNames(chatRoomId);

        return new ChatRoomDetailsDto(chatRoomId, participants, messages);
    }


    private List<ChatMessageResDto> getChatMessagesWithUserNames(Long chatRoomId) {
        // 1. 채팅 메시지 조회
        List<ChatMessage> chatMessages = chatMessageRepository.findByChatRoomId(chatRoomId);

        // 2. userCode 목록 추출 및 유저 정보 조회
        Map<String, String> userCodeToNameMap = getUserCodeToNameMap(
                chatMessages.stream()
                        .map(ChatMessage::getUserCode)
                        .distinct()
                        .toList()
        );

        // 3. 채팅 메시지와 사용자 이름 매핑
        return chatMessages.stream()
                .map(chatMessage -> {
                    String userName = userCodeToNameMap.getOrDefault(chatMessage.getUserCode(), "알수 없음");
                    return convertToDto(chatMessage, userName);
                })
                .toList();
    }

    private Map<String, String> getUserCodeToNameMap(List<String> userCodes) {
        return userRepository.findAllByUserCodeIn(userCodes).stream()
                .collect(Collectors.toMap(UserInfo::getUserCode, UserInfo::getUserName));
    }

    private ChatMessageResDto convertToDto(ChatMessage chatMessage, String userName) {
        return ChatMessageResDto.builder()
                .messageId(chatMessage.getId().toHexString())
                .chatRoomId(chatMessage.getChatRoomId())
                .userCode(chatMessage.getUserCode())
                .userName(userName)
                .messageStatus(chatMessage.getMessageStatus().toString())
                .messageContent(chatMessage.getMessageContent())
                .messageCreatedTime(chatMessage.getMessageCreatedTime().toString())
                .messageDeletedTime(chatMessage.getMessageDeletedTime() != null
                        ? chatMessage.getMessageDeletedTime().toString()
                        : null)
                .build();
    }

    @Transactional(readOnly = true)
    public List<ChatRoomDTO> getMyChatRooms(String loginUserCode) {

        if (loginUserCode == null) {
            throw new IllegalArgumentException("채팅방 조회를 위한 사용자의 userCode값이 존재하지 않습니다. JWT authentication is required.");
        }
        return chatRoomQueryMapper.findAllChatRoomList(loginUserCode);
    }
}
