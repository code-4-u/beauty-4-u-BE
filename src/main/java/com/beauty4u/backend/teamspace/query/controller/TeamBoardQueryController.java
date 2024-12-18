package com.beauty4u.backend.teamspace.query.controller;

import com.beauty4u.backend.common.response.ApiResponse;
import com.beauty4u.backend.common.response.ResponseUtil;
import com.beauty4u.backend.common.success.SuccessCode;
import com.beauty4u.backend.teamspace.query.dto.teamboard.TeamBoardDetailResDTO;
import com.beauty4u.backend.teamspace.query.dto.teamboard.TeamBoardFilterDTO;
import com.beauty4u.backend.teamspace.query.dto.teamboard.TeamBoardListResDTO;
import com.beauty4u.backend.teamspace.query.service.TeamBoardQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/teamspace/board")
@RequiredArgsConstructor
@Tag(name = "TeamSpace", description = "팀스페이스 관련 API")
public class TeamBoardQueryController {

    private final TeamBoardQueryService teamBoardQueryService;

    @Operation(summary = "팀 게시판 글 목록 조회", description = "팀 게시판 글 목록을 조회한다.")
    @GetMapping("/list")
    public ResponseEntity<ApiResponse<TeamBoardListResDTO>> findTeamBoardList(TeamBoardFilterDTO teamBoardFilterDTO) {

        TeamBoardListResDTO teamBoardListResDTO = teamBoardQueryService.findTeamBoardList(teamBoardFilterDTO);

        return ResponseUtil.successResponse(SuccessCode.TEAMBOARD_FIND_LIST_SUCCESS, teamBoardListResDTO);
    }

    @Operation(summary = "팀 게시판 글 상세 조회", description = "등록된 팀 게시판 글을 상세 조회한다.")
    @GetMapping("/{teamBoardId}")
    public ResponseEntity<ApiResponse<TeamBoardDetailResDTO>> findTeamBoardDetail(
            @PathVariable Long teamBoardId) {

        TeamBoardDetailResDTO teamBoardDetailResDTO = teamBoardQueryService.findTeamBoardDetail(teamBoardId);

        return ResponseUtil.successResponse(SuccessCode.TEAMBOARD_FIND_DETAIL_SUCCESS, teamBoardDetailResDTO);
    }
}
