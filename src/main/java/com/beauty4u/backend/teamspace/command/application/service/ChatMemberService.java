package com.beauty4u.backend.teamspace.command.application.service;

import com.beauty4u.backend.teamspace.command.domain.aggregate.ChatMember;
import com.beauty4u.backend.teamspace.command.domain.repository.ChatMemberRepository;
import com.beauty4u.backend.user.command.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatMemberService {

    private final UserRepository userRepository;
    private final ChatMemberRepository chatMemberRepository;

    public void inviteUsersToChatRoom(String loginUserCode, Long chatRoomId, String inviteUserCode) {

        // 1. 유저가 존재하는지 확인
        if (!userRepository.existsByUserCode(inviteUserCode)) {
            throw new IllegalArgumentException("User does not exist.");
        }

        // 2. 유저가 이미 채팅방 멤버인지 확인
        if (chatMemberRepository.existsByChatRoomIdAndUserCode(chatRoomId, inviteUserCode)) {
            throw new IllegalArgumentException("User is already a member of this chat room.");
        }

        // 3. 새로운 멤버 추가
        ChatMember newMember = ChatMember.builder()
                .chatRoomId(chatRoomId)
                .userCode(inviteUserCode)
                .build();
        chatMemberRepository.save(newMember);
    }
}
