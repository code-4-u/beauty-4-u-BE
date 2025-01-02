package com.beauty4u.backend.teamspace.command.application.service;

import com.beauty4u.backend.teamspace.command.application.dto.chatmember.ChatMemberResDTO;
import com.beauty4u.backend.teamspace.command.application.dto.chatroom.FindChatRoomDTO;
import com.beauty4u.backend.teamspace.command.domain.aggregate.ChatMember;
import com.beauty4u.backend.teamspace.command.domain.repository.ChatMemberRepository;
import com.beauty4u.backend.teamspace.command.domain.service.ChatMemberDomainService;
import com.beauty4u.backend.teamspace.command.domain.service.ChatMessageDomainService;
import com.beauty4u.backend.teamspace.command.domain.service.ChatRoomDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChatMemberService {

    private final ChatMemberRepository chatMemberRepository;
    private final ChatMemberDomainService chatMemberDomainService;
    private final ChatRoomDomainService chatRoomDomainService;
    private final ChatMessageDomainService chatMessageDomainService;

    public void saveChatMember(String loginUserCode, Long chatRoomId, List<String> inviteUserCodes) {

        // 초대하는 사람이 채팅방에 존재하는지도 확인해야 함

        // 2. 채팅방이 존재하는지 확인
        FindChatRoomDTO findChatRoomDTO = chatRoomDomainService.findChatRoomById(chatRoomId);
        if (findChatRoomDTO == null) {
            throw new IllegalArgumentException("존재하지 않는 채팅방입니다.");
        }

        // 3. 초대할 사용자 검증 및 중복 확인
        List<String> validUserCodes = inviteUserCodes.stream()
                .filter(chatMemberDomainService::isValidUser)
                .filter(userCode -> !chatMemberDomainService.checkChatMember(chatRoomId, userCode))
                .toList();

        if (validUserCodes.isEmpty()) {
            throw new IllegalArgumentException("초대할 사용자가 유효하지 않거나 이미 채팅방에 존재합니다.");
        }

        // 5. 새로운 멤버 추가
        List<ChatMember> newMembers = validUserCodes.stream()
                .map(userCode -> ChatMember.builder()
                        .chatRoomId(chatRoomId)
                        .userCode(userCode)
                        .build())
                .collect(Collectors.toList());

        chatMemberRepository.saveAll(newMembers);
    }

    // 채팅방에서 나가기
    public void leaveChatRoom(Long chatRoomId, String loginUserCode) {

        // 1. 채팅방이 존재하는지 확인
        chatRoomDomainService.findChatRoomById(chatRoomId);

        // 2. 채팅방에서 해당 유저를 제거
        ChatMemberResDTO chatMemberResDTO = chatMemberDomainService.findByChatMember(chatRoomId, loginUserCode);
        if (chatMemberResDTO == null) {
            throw new IllegalArgumentException("존재하지 않는 유저입니다.");
        }
        System.out.println(chatMemberResDTO);
        System.out.println(chatMemberResDTO.getChatMemberId());
        // 해당 유저가 채팅방에 있을 경우 삭제 로직 실행
        chatMemberDomainService.deleteChatMember(chatMemberResDTO.getChatMemberId());

        // 3. 채팅방에 남은 유저 수 확인
        long remainingMembers = chatMemberDomainService.chatMembersByChatRoomId(chatRoomId);

        // 4. 남은 유저가 없으면 채팅방 및 채팅 메시지 삭제
        if (remainingMembers == 0) {
            // 채팅방 삭제
            chatRoomDomainService.deleteChatRoom(chatRoomId);
            // 해당 채팅방의 메시지 삭제
            chatMessageDomainService.deleteByChatRoomId(chatRoomId);
        }
    }
}
