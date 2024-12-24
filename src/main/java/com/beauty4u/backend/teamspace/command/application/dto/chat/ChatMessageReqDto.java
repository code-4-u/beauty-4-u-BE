package com.beauty4u.backend.teamspace.command.application.dto.chat;

import com.beauty4u.backend.common.aggregate.entity.BaseEntity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ChatMessageReqDto extends BaseEntity {

    private Long id;
    private String userCode;
    private String messageContent;

}

