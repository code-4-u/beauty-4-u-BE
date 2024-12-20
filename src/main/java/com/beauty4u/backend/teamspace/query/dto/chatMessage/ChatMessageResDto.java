package com.beauty4u.backend.teamspace.query.dto.chatMessage;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatMessageResDto {
    private String messageId;
    private Long teamspaceId;
    private String userCode;
    private String userName; // 메세지 발신자
    private String messageStatus;
    private String messageContent;
    private String messageCreatedTime;
    private String messageDeletedTime;

}
