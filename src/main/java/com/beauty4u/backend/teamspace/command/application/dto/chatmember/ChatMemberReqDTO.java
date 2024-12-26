package com.beauty4u.backend.teamspace.command.application.dto.chatmember;

import lombok.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatMemberReqDTO {

    private List<String> invitedUserCodes; // 초대할 사용자들의 고유 코드 리스트
}
