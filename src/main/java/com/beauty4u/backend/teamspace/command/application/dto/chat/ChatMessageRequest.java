package com.beauty4u.backend.teamspace.command.application.dto.chat;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ChatMessageRequest {

    //    private Long teamspaceId;
    private String userCode;
    //    private Long scrapId;
//    private String messageStatus;
    private String messageContent;
//    private LocalDateTime messageCreatedTime;
//    private LocalDateTime messageDeletedTime;

}

