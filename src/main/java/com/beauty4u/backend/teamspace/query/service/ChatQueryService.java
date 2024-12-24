package com.beauty4u.backend.teamspace.query.service;

import com.beauty4u.backend.teamspace.command.domain.aggregate.ChatMessage;
import com.beauty4u.backend.teamspace.command.domain.repository.ChatMessageRepository;
import com.beauty4u.backend.teamspace.query.dto.chatMessage.ChatMessageResDto;
import com.beauty4u.backend.user.command.domain.aggregate.UserInfo;
import com.beauty4u.backend.user.command.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChatQueryService {

    private final ChatMessageRepository chatMessageRepository;
    private final UserRepository userRepository;

    public List<ChatMessageResDto> getChatHistory(Long teamspaceId) {
        // 1. 채팅 메세지 조회
        List<ChatMessage> chatMessages = chatMessageRepository.findByTeamspaceId(teamspaceId);

        // 2. userCode 목록 추출 (중복 제거)
        List<String> userCodes = chatMessages.stream()
                .map(ChatMessage::getUserCode)
                .distinct()
                .toList();

        // 3. UserRepository를 통해 유저 정보를 한 번에 로드
        List<UserInfo> users = userRepository.findAllByUserCodeIn(userCodes);

        // 4. userCode와 userName 매핑 (Map 생성)
        Map<String, String> userCodeToNameMap = users.stream()
                .collect(Collectors.toMap(UserInfo::getUserCode, UserInfo::getUserName));

        // 5. 채팅 메시지와 사용자 이름 매핑
        return chatMessages.stream()
                .map(chatMessage -> {
                    String userName = userCodeToNameMap.getOrDefault(chatMessage.getUserCode(), "알수 없음");
                    return convertToDto(chatMessage, userName);
                })
                .toList();
    }

    private ChatMessageResDto convertToDto(ChatMessage chatMessage, String userName) {
        ChatMessageResDto dto = new ChatMessageResDto();
        dto.setMessageId(chatMessage.getId().toHexString());
        dto.setChatRoomId(chatMessage.getChatRoomId());
        dto.setUserCode(chatMessage.getUserCode());
        dto.setUserName(userName); // 유저 이름 설정
        dto.setMessageStatus(chatMessage.getMessageStatus().toString());
        dto.setMessageContent(chatMessage.getMessageContent());
        dto.setMessageCreatedTime(chatMessage.getMessageCreatedTime().toString());
        dto.setMessageDeletedTime(
                chatMessage.getMessageDeletedTime() != null
                        ? chatMessage.getMessageDeletedTime().toString()
                        : null
        );
        return dto;
    }
}
