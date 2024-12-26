package com.beauty4u.backend.teamspace.query.dto.chatMessage;

import com.beauty4u.backend.file.command.application.dto.FileDTO;
import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatMessageResDto {
    private String messageId;
    private Long chatRoomId;
    private String userCode;
    private String userName; // 메세지 발신자
    private String messageStatus;
    private String messageContent;
    private String messageCreatedTime;
    private String messageDeletedTime;

    // 첨부 파일 정보
    private List<FileDTO> attachedFiles;
}
