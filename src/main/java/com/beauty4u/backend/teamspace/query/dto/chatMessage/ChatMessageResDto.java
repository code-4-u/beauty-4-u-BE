package com.beauty4u.backend.teamspace.query.dto.chatMessage;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatMessageResDto {
    private Long messageId;
    private Long teamspaceId;
//    private String userCode; // 메세지 발신자
    private String senderName;
    private String scarpId;
    private String messageStatus;
    private String messageContent;
    private String messageCreatedTime;
    private String messageDeletedTime;

}
