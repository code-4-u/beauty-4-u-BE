package com.beauty4u.backend.teamspace.query.dto.chatroom;

import com.beauty4u.backend.teamspace.query.dto.chatMessage.ChatMessageResDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChatRoomDetailsDto {
    private Long chatRoomId;                  // 채팅방 ID
    private List<ChatRoomUserInfoDto> participants; // 채팅방 참여자 정보
    private List<ChatMessageResDto> messages;       // 채팅 메시지 리스트
}