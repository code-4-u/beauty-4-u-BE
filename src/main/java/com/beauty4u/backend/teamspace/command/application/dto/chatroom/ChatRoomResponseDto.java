package com.beauty4u.backend.teamspace.command.application.dto.chatroom;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatRoomResponseDto {

    private Long chatRoomId;            // 채팅방 고유 ID
    private List<String> memberUserCodes; // 채팅방 멤버들의 사용자 코드 리스트
}