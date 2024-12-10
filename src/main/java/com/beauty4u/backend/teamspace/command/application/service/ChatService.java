package com.beauty4u.backend.teamspace.command.application.service;

import com.beauty4u.backend.teamspace.command.application.dto.chat.ChatMessageRequest;
import com.beauty4u.backend.teamspace.command.domain.aggregate.ChatMessage;
import com.beauty4u.backend.teamspace.command.domain.aggregate.Teamspace;
import com.beauty4u.backend.teamspace.command.domain.repository.ChatMessageRepository;
import com.beauty4u.backend.teamspace.command.domain.repository.TeamSpaceRepository;
import com.beauty4u.backend.user.command.domain.aggregate.UserInfo;
import com.beauty4u.backend.user.command.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatService {

    private final UserRepository userRepository;
    private final TeamSpaceRepository teamSpaceRepository;
    private final ChatMessageRepository chatMessageRepository;
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final ModelMapper modelMapper;

    public void save(Long teamspaceId, ChatMessageRequest chatMessageRequest) {
        // 유저와 팀스페이스 조회
        UserInfo sender = userRepository.findByUserCode(chatMessageRequest.getUserCode())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Teamspace teamspace = teamSpaceRepository.findById(teamspaceId)
                .orElseThrow(() -> new RuntimeException("Teamspace not found"));

        // 메시지 생성 및 저장
        ChatMessage chatMessage = ChatMessage.builder()
                .messageContent(chatMessageRequest.getMessageContent())
                .userCode(sender.getUserCode())
                .teamspaceId(teamspace.getTeamspaceId())
                .messageCreatedTime(ZonedDateTime.now(ZoneId.of("Asia/Seoul")))
                .build();

        chatMessageRepository.save(chatMessage);

        // STOMP 메시지 전송
        // 해당 주소를 수신하고 있는 유저들에게 보내짐
        String destination = "/sub/teamspace/" + teamspaceId;
        simpMessagingTemplate.convertAndSend(destination, chatMessageRequest);

        // 로그 기록
        log.info("Message [{}] saved and sent to destination [{}]", chatMessage.getMessageContent(), destination);
    }
}
