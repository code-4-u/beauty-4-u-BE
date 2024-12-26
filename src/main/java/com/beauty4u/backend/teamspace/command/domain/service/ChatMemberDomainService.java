package com.beauty4u.backend.teamspace.command.domain.service;

import com.beauty4u.backend.common.aggregate.YnType;
import com.beauty4u.backend.teamspace.command.application.dto.chatmember.ChatMemberResDTO;
import com.beauty4u.backend.teamspace.command.domain.aggregate.ChatMember;
import com.beauty4u.backend.teamspace.command.domain.repository.ChatMemberRepository;
import com.beauty4u.backend.user.command.domain.aggregate.UserInfo;
import com.beauty4u.backend.user.command.domain.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatMemberDomainService {

    private final ChatMemberRepository chatMemberRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public boolean checkChatMember(Long chatRoomId, String inviteUserCode) {
        return chatMemberRepository.existsByChatRoomIdAndUserCode(chatRoomId, inviteUserCode);
    }

    public boolean isValidUser(String userCode) {
        Optional<UserInfo> userInfoOptional = userRepository.findByUserCode(userCode);

        // 유저가 존재하고, user_expired_yn 값이 'N'인 경우만 초대 가능
        return userInfoOptional
                .filter(userInfo -> userInfo.getUserExpiredYn() == YnType.N)
                .isPresent();
    }

    public ChatMemberResDTO findByChatMember(Long chatRoomId, String loginUserCode) {

        ChatMember chatMember = chatMemberRepository.findByChatRoomIdAndUserCode(chatRoomId, loginUserCode)
                .orElseThrow(() -> new IllegalArgumentException("채팅방에서 해당 유저를 찾을 수 없습니다."));

        return modelMapper.map(chatMember, ChatMemberResDTO.class);

    }

    @Transactional
    public void deleteChatMember(Long chatMemberId) {
        chatMemberRepository.deleteById(chatMemberId);
    }

    public Long chatMembersByChatRoomId(Long chatRoomId) {
        return chatMemberRepository.countByChatRoomId(chatRoomId);
    }

}