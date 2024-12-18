package com.beauty4u.backend.teamspace.query.mapper;

import com.beauty4u.backend.teamspace.query.dto.teamboard.TeamBoardDetailDTO;
import com.beauty4u.backend.teamspace.query.dto.teamboard.TeamBoardDetailResDTO;
import com.beauty4u.backend.teamspace.query.dto.teamboard.TeamBoardListDTO;
import com.beauty4u.backend.teamspace.query.dto.teamboard.TeamBoardReplyListDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface TeamBoardQueryMapper {

    List<TeamBoardListDTO> findTeamBoardList(
            @Param("teamBoardTitle") String teamBoardTitle,
            @Param("publishStatus") String publishStatus,
            @Param("startDateTime") LocalDateTime startDateTime,
            @Param("endDateTime") LocalDateTime endDateTime,
            @Param("sort") String sort,
            @Param("order") String order,
            @Param("offset") Long offset,
            @Param("count") Long count);


    Long findTeamBoardListTotalCount(
            @Param("teamBoardTitle") String teamBoardTitle,
            @Param("publishStatus") String publishStatus,
            @Param("startDateTime") LocalDateTime startDateTime,
            @Param("endDateTime") LocalDateTime endDateTime,
            @Param("sort") String sort,
            @Param("order") String order);

    TeamBoardDetailDTO findTeamBoardDetail(
            @Param("teamBoardId") Long teamBoardId);

    List<TeamBoardReplyListDTO> findTeamBoardReplyList(
            @Param("teamBoardId") Long teamBoardId);
}
