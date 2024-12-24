package com.beauty4u.backend.teamspace.query.mapper;

import com.beauty4u.backend.teamspace.query.dto.chatroom.ChatRoomUserInfoDto;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface ChatRoomQueryMapper {

    //부서코드로 채팅참여자 정보 조회
    List<ChatRoomUserInfoDto> findAllChatRoomUser(String deptCode);
}
