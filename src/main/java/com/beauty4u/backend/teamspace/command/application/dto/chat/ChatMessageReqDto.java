package com.beauty4u.backend.teamspace.command.application.dto.chat;

import com.beauty4u.backend.common.aggregate.entity.BaseEntity;
import com.beauty4u.backend.file.command.application.dto.FileDTO;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ChatMessageReqDto extends BaseEntity {

    private String userCode; // 메세지를 보낸 유저 코드
    private String messageContent;
    private Long chatRoomId;
    private List<FileDTO> attachedFiles; // 파일 정보 (URL, ID 등)

    //private String messageType; // 메시지 타입 (Optional)
}

