package com.beauty4u.backend.teamspace.command.application.dto.chatroom;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatRoomReqDTO {

    private String chatRoomName; // 채팅방 이름
    private List<String> invitedUsers; // 초대할 사용자 정보 리스트

}
