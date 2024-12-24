package com.beauty4u.backend.teamspace.command.application.service;

import com.beauty4u.backend.teamspace.command.domain.aggregate.ChatRoom;
import com.beauty4u.backend.teamspace.command.domain.repository.ChatRoomRepository;
import com.beauty4u.backend.user.command.domain.aggregate.Dept;
import com.beauty4u.backend.user.command.domain.repository.DeptRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;
    private final DeptRepository deptRepository;

    // 채팅방 생성
    @Transactional
    public ChatRoom createTeamspace(String deptCode) {

        Dept dept = deptRepository.findById(deptCode)
                .orElseThrow(() -> new EntityNotFoundException("해당 부서 코드가 존재하지 않습니다."));

        ChatRoom chatRoom = new ChatRoom(null);

        return chatRoomRepository.save(chatRoom);
    }

    // 채팅방 삭제
    @Transactional
    public void deleteTeamspace(Long teamSpaceId) {
        ChatRoom chatRoom = chatRoomRepository.findById(teamSpaceId)
                .orElseThrow(() -> new EntityNotFoundException("id에 해당하는 채팅방이 없습니다."));
        chatRoomRepository.delete(chatRoom);
    }
}
