package com.beauty4u.backend.teamspace.query.mapper;

import com.beauty4u.backend.teamspace.query.dto.teamspace.TeamSpaceUserInfoDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TeamSpaceQueryMapper {

    //채팅방 id로 채팅참여자 정보 조회
    List<TeamSpaceUserInfoDto> findAllTeamSpaceUser(String deptCode);

    // 팀스페이스 ID를 이용하여 부서 코드 조회
    String findTeamSpaceDeptCode(@Param("teamspaceId") String teamspaceId);

    // 부서 코드를 이용하여 팀스페이스 ID 조회
    Long findTeamSpaceId(@Param("dept_code") String deptCode);
}
