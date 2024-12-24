package com.beauty4u.backend.teamspace.query.dto.chatroom;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class ChatRoomResponse {

    private Long chatRoomId;
    private String deptCode;
}
