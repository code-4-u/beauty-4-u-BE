package com.beauty4u.backend.teamspace.command.domain.aggregate;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "chatMessage")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
// 몽고 DB의 경우 연관관계 매핑을 지원하지 않는다.
public class ChatMessage{

    @Id
    private ObjectId id; // MongoDB에서 자동 생성되는 _id 필드를 매핑 (String 또는 ObjectId 사용)
                         // messageId 값으로 사용
    @NotNull
    private Long chatRoomId; // 채팅방 번호

    private String userCode; // sender 사용자 코드

    @Enumerated(value = EnumType.STRING)  // 몽고DB에서 기본적으로 문자열 처리를 해준다.
    private MessageStatus messageStatus = MessageStatus.ACTIVE; // 메시지 상태 (ACTIVE, INACTIVE)

    @NotNull
    private String messageContent; // 메시지 내용

    @CreatedDate // 생성 시간 자동 설정
    private LocalDateTime messageCreatedTime;

    private LocalDateTime messageDeletedTime; // 삭제 시간

}