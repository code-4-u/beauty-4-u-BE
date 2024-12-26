package com.beauty4u.backend.teamspace.command.domain.repository;

import com.beauty4u.backend.teamspace.command.domain.aggregate.ChatMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ChatMemberRepository extends JpaRepository<ChatMember, Long> {

    // 특정 채팅방에 유저가 이미 초대되었는지 확인
    boolean existsByChatRoomIdAndUserCode(Long chatRoomId, String userCode);

    // 특정 채팅방에 속한 유저 리스트 조회
    List<ChatMember> findAllByChatRoomId(Long chatRoomId);
}