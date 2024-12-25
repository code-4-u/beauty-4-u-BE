package com.beauty4u.backend.teamspace.command.application.dto.chatroom;

import lombok.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InviteUsersToChatRoomReqDto {

    private List<String> invitedUserCodes; // 초대할 사용자들의 고유 코드 리스트
}
