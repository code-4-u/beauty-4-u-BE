package com.beauty4u.backend.teamspace.command.application.dto.chat;

import lombok.*;
import java.time.ZonedDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ChatMessageReqDto {

    private String userCode; // senderId 변경
    private String messageContent;
    private ZonedDateTime messageCreatedTime;

}

