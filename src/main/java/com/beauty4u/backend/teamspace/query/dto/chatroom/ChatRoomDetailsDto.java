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
    private String deptName;
    private List<ChatRoomUserInfoDto> participants;
    private List<ChatMessageResDto> messages;
}