package com.beauty4u.backend.teamspace.command.application.dto.chat;

import com.beauty4u.backend.common.aggregate.entity.BaseEntity;
import com.beauty4u.backend.file.command.application.dto.FileDTO;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ChatMessageReqDto extends BaseEntity {

    private Long chatRoomId; // 채팅방 번호
    private String userCode; // 메세지를 보낸 유저 코드
    private String userName;
    private String messageContent;
    private List<FileDTO> attachedFiles; // 파일 정보 (URL, ID 등)

    private LocalDateTime messageCreatedTime; // UTC 시간대 정보 포함

    //private String messageType; // 메시지 타입 (Optional)
}

