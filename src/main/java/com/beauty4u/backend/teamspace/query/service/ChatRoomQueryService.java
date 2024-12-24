package com.beauty4u.backend.teamspace.query.service;

import com.beauty4u.backend.teamspace.command.domain.aggregate.ChatMessage;
import com.beauty4u.backend.teamspace.command.domain.repository.ChatMessageRepository;
import com.beauty4u.backend.teamspace.query.dto.chatMessage.ChatMessageResDto;
import com.beauty4u.backend.teamspace.query.dto.chatroom.ChatRoomDetailsDto;
import com.beauty4u.backend.teamspace.query.dto.chatroom.ChatRoomUserInfoDto;
import com.beauty4u.backend.teamspace.query.mapper.ChatRoomQueryMapper;
import com.beauty4u.backend.user.command.domain.aggregate.UserInfo;
import com.beauty4u.backend.user.command.domain.repository.UserRepository;
import com.beauty4u.backend.user.query.mapper.DeptQueryMapper;
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
    private final DeptQueryMapper deptQueryMapper;
    private final UserRepository userRepository;
    private final ChatMessageRepository chatMessageRepository;

    // 부서 코드로 채팅참여자 정보 조회
    @Transactional(readOnly = true)
    public List<ChatRoomUserInfoDto> findAllChatRoomUser(String deptCode) {
        return chatRoomQueryMapper.findAllChatRoomUser(deptCode);
    }


    // 팀스페이스 세부 정보 조회
    @Transactional(readOnly = true)
    public ChatRoomDetailsDto getChatRoomDetails(Long chatRoomId, String deptCode) {
        // 1. 부서 이름 조회
        String deptName = getDeptNameByCode(deptCode);

        // 2. 참여자 정보 조회
        List<ChatRoomUserInfoDto> participants = findAllChatRoomUser(deptCode);

        // 3. 채팅 메시지와 사용자 이름 매핑
        List<ChatMessageResDto> messages = getChatMessagesWithUserNames(chatRoomId);

        return new ChatRoomDetailsDto(deptName, participants, messages);
    }

    private String getDeptNameByCode(String deptCode) {
        return deptQueryMapper.findDeptName(deptCode).getDeptName();
    }

    private List<ChatMessageResDto> getChatMessagesWithUserNames(Long teamspaceId) {
        // 1. 채팅 메시지 조회
        List<ChatMessage> chatMessages = chatMessageRepository.findByChatRoomId(teamspaceId);

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
}
