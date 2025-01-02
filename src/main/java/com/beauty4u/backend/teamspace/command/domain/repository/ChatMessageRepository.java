package com.beauty4u.backend.teamspace.command.domain.repository;

import com.beauty4u.backend.teamspace.command.domain.aggregate.ChatMessage;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatMessageRepository extends MongoRepository<ChatMessage, ObjectId> {
    List<ChatMessage> findByChatRoomId(Long findByChatRoomId);

    void deleteByChatRoomId(Long chatRoomId); // 해당 채팅방의 메시지 삭제
}
