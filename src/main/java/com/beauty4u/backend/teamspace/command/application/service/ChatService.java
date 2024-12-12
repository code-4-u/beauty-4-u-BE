package com.beauty4u.backend.teamspace.command.application.service;

import com.beauty4u.backend.teamspace.command.application.dto.chat.ChatMessageReqDto;
import com.beauty4u.backend.teamspace.command.domain.aggregate.ChatMessage;
import com.beauty4u.backend.teamspace.command.domain.aggregate.Teamspace;
import com.beauty4u.backend.teamspace.command.domain.repository.ChatMessageRepository;
import com.beauty4u.backend.teamspace.command.domain.repository.TeamSpaceRepository;
import com.beauty4u.backend.user.command.domain.aggregate.UserInfo;
import com.beauty4u.backend.user.command.domain.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatService {

    private final UserRepository userRepository;
    private final TeamSpaceRepository teamSpaceRepository;
    private final ChatMessageRepository chatMessageRepository;
    private final SimpMessagingTemplate simpMessagingTemplate;

    public void sendMessage(Long teamspaceId, ChatMessageReqDto chatMessageReqDto) {

        // 팀스페이스 찾기
        Teamspace teamspace = teamSpaceRepository.findById(teamspaceId)
                .orElseThrow(() -> new EntityNotFoundException("팀스페이스가 존재하지 않습니다."));

        // 보낸 사람 찾기
//        UserInfo sender = userRepository.findByUserCode(chatMessageReqDto.getUserCode())
//                .orElseThrow(() -> new EntityNotFoundException("유저가 존재하지 않습니다."));


        // 메시지 생성 및 저장
        ChatMessage chatMessage = ChatMessage.builder()
                .messageContent(chatMessageReqDto.getMessageContent())
//                .userCode(sender.getUserCode())
                .teamspaceId(teamspace.getId())
                .build();

        chatMessageRepository.save(chatMessage);

        // STOMP 메시지 전송
        // 해당 주소를 수신하고 있는 유저들에게 보내짐
        simpMessagingTemplate.convertAndSend("/sub/teamspace/" + teamspaceId, chatMessageReqDto);
        log.info("Message [{}] saved and sent to destination [{}]", chatMessage.getMessageContent(), "/sub/teamspace/" + teamspaceId);
    }
}
