package com.beauty4u.backend.teamspace.command.domain.repository;

import com.beauty4u.backend.teamspace.command.domain.aggregate.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
    boolean existsByDeptCode(String deptCode);
}
