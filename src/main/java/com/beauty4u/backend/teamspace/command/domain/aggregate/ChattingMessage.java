package com.beauty4u.backend.teamspace.command.domain.aggregate;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Document(collection = "chatting")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChattingMessage {

    @Id
    private String id; // MongoDB에서 자동 생성되는 _id 필드를 매핑 (String 또는 ObjectId 사용)

//    @Field("message_id")
    private Long messageId; // 메시지 번호

//    @Field("teamspace_id")
    private Long teamspaceId; // 팀스페이스 번호

//    @Field("user_code")
    private String userCode; // 사용자 코드

//    @Field("scrap_id")
    private Long scrapId; // 스크랩 번호

//    @Field("message_status")
    private String messageStatus; // 메시지 상태 (ACTIVE, DELETED 등)

//    @Field("message_content")
    private String messageContent; // 메시지 내용

//    @Field("message_created_time")
    private LocalDateTime messageCreatedTime; // 메시지 작성 일시

//    @Field("message_deleted_time")
    private LocalDateTime messageDeletedTime; // 메시지 삭제 일시 (삭제되지 않은 경우 null)
}