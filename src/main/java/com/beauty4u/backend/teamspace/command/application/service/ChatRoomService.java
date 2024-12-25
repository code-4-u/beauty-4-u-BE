package com.beauty4u.backend.teamspace.command.application.service;

import com.beauty4u.backend.teamspace.command.application.dto.chatroom.ChatRoomResponseDto;
import com.beauty4u.backend.teamspace.command.application.dto.chatroom.InviteUsersToChatRoomReqDto;
import com.beauty4u.backend.teamspace.command.domain.aggregate.ChatMember;
import com.beauty4u.backend.teamspace.command.domain.aggregate.ChatRoom;
import com.beauty4u.backend.teamspace.command.domain.repository.ChatMemberRepository;
import com.beauty4u.backend.teamspace.command.domain.repository.ChatRoomRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;
    private final ChatMemberRepository chatMemberRepository;

    // 채팅방 생성
    @Transactional
    public ChatRoomResponseDto createChatRoom(String creatorUserCode, InviteUsersToChatRoomReqDto reqDto) {

        // 1. 채팅방 생성
        ChatRoom chatRoom = new ChatRoom();
        chatRoom = chatRoomRepository.save(chatRoom);

        // 2. 채팅방 ID를 로컬 변수에 저장
        Long chatRoomId = chatRoom.getId();

        // 3. 채팅 멤버 추가 (자기 자신)
        ChatMember creatorMember = ChatMember.builder()
                .chatRoomId(chatRoomId)
                .userCode(creatorUserCode)
                .build();
        chatMemberRepository.save(creatorMember);

        // 4. 요청된 사용자 추가
        List<ChatMember> invitedMembers = reqDto.getInvitedUserCodes().stream()
                .map(userCode -> ChatMember.builder()
                        .chatRoomId(chatRoomId)
                        .userCode(userCode)
                        .build())
                .toList();
        chatMemberRepository.saveAll(invitedMembers);

        // 5. 응답 DTO 생성
        List<String> allMembers = new ArrayList<>();
        allMembers.add(creatorUserCode);
        allMembers.addAll(reqDto.getInvitedUserCodes());

        return ChatRoomResponseDto.builder()
                .chatRoomId(chatRoom.getId())
                .memberUserCodes(allMembers)
                .build();
    }

    // 채팅방 삭제
    @Transactional
    public void deleteTeamspace(Long teamSpaceId) {
        ChatRoom chatRoom = chatRoomRepository.findById(teamSpaceId)
                .orElseThrow(() -> new EntityNotFoundException("id에 해당하는 채팅방이 없습니다."));
        chatRoomRepository.delete(chatRoom);
    }
}
