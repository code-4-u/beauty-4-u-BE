package com.beauty4u.backend.teamspace.command.application.dto.chat;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ChatMessageReqDto {

    private String userCode;
    private String messageContent;

}

