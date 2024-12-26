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

@Service
@RequiredArgsConstructor
public class ChatMemberService {

    private final ChatMemberRepository chatMemberRepository;
    private final ChatMemberDomainService chatMemberDomainService;
    private final ChatRoomDomainService chatRoomDomainService;
    private final ChatMessageDomainService chatMessageDomainService;

    public void saveChatMember(String loginUserCode, Long chatRoomId, String inviteUserCode) {

        // 초대하는 사람이 채팅방에 존재하는지도 확인해야 함

        // 1. 유효한 사용자인지 확인
        if (!chatMemberDomainService.isValidUser(inviteUserCode)) {
            throw new IllegalArgumentException("해당 유저가 존재하지 않거나 만료된 사용자입니다.");
        }

        // 2. 채팅방이 존재하는지 확인
        FindChatRoomDTO findChatRoomDTO = chatRoomDomainService.findChatRoomById(chatRoomId);
        if (findChatRoomDTO == null) {
            throw new IllegalArgumentException("존재하지 않는 채팅방입니다.");
        }

        // 3. 채팅방에 초대하는 사람이 채팅방 멤버인지 확인
        if (!chatMemberDomainService.checkChatMember(chatRoomId, loginUserCode)) {
            throw new IllegalArgumentException("채팅방에 존재하는 유저만 다른 유저를 초대할 수 있습니다.");
        }

        // 4. 유저가 이미 채팅방 멤버인지 확인
        if (chatMemberDomainService.checkChatMember(chatRoomId, inviteUserCode)) {
            throw new IllegalArgumentException("이미 채팅방에 존재하는 유저입니다.");
        }

        // 5. 새로운 멤버 추가
        ChatMember newMember = ChatMember.builder()
                .chatRoomId(chatRoomId)
                .userCode(inviteUserCode)
                .build();
        chatMemberRepository.save(newMember);
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
