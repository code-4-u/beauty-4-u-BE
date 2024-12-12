package com.beauty4u.backend.teamspace.query.mapper;

import com.beauty4u.backend.teamspace.query.dto.teamspace.TeamSpaceUserInfoDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TeamSpaceQueryMapper {

    //채팅방 id로 채팅참여자 정보 조회
    List<TeamSpaceUserInfoDto> findAllTeamSpaceUser(String deptCode);

}
