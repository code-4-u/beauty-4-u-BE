package com.beauty4u.backend.teamspace.command.application.dto.chatmember;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChatMemberResDTO {

    private Long chatMemberId;
    private Long chatRoomId;
    private String userCode;
}
