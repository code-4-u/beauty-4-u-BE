package com.beauty4u.backend.teamspace.query.mapper;

import com.beauty4u.backend.teamspace.query.dto.chatroom.ChatRoomDTO;
import com.beauty4u.backend.teamspace.query.dto.chatroom.ChatRoomUserInfoDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ChatRoomQueryMapper {

    // 채팅방 번호로 채팅참여자 정보 조회
    List<ChatRoomUserInfoDto> findAllChatRoomUser(Long chatRoomId);

    // 유저가 채팅방에 참여 중인지 확인
    boolean isUserInChatRoom(@Param("chatRoomId") Long chatRoomId, @Param("userCode") String userCode);

    // 유저가 속한 채팅방 리스트 조회
    List<ChatRoomDTO> findAllChatRoomList(String loginUserCode);
}
