package com.beauty4u.backend.teamspace.command.domain.aggregate;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.ZonedDateTime;

@Document(collection = "chatMessage")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatMessage {

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
//    @Enumerated(value = EnumType.STRING) 몽고DB에서 기본적으로 문자열 처리
    private MessageStatus messageStatus; // 메시지 상태 (ACTIVE, DELETED 등)

//    @Field("message_content")
    private String messageContent; // 메시지 내용

//    @Field("message_created_time")
    private ZonedDateTime messageCreatedTime; // 메시지 작성 일시

//    @Field("message_deleted_time")
    private ZonedDateTime  messageDeletedTime; // 메시지 삭제 일시 (삭제되지 않은 경우 null)
}