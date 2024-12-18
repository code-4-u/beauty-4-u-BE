package com.beauty4u.backend.teamspace.command.application.controller;

import com.beauty4u.backend.common.response.ApiResponse;
import com.beauty4u.backend.common.response.ResponseUtil;
import com.beauty4u.backend.common.success.SuccessCode;
import com.beauty4u.backend.common.util.CustomUserUtil;
import com.beauty4u.backend.teamspace.command.application.dto.teamboard.TeamBoardReqDTO;
import com.beauty4u.backend.teamspace.command.application.service.TeamBoardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/teamspace")
@RequiredArgsConstructor
@Tag(name = "TeamSpace", description = "팀스페이스 관련 API")
public class TeamBoardCommandController {

    private final TeamBoardService teamBoardService;

    @Operation(summary = "팀 게시글 등록", description = "회원이 팀 게시판에 게시글을 등록한다.")
    @PostMapping
    public ResponseEntity<ApiResponse<Long>> saveTeamBoard(
            @RequestBody @Valid TeamBoardReqDTO teamBoardReqDTO) {

        String loginUserCode = CustomUserUtil.getCurrentUserCode();

        Long teamBoardId = teamBoardService.saveTeamBoard(loginUserCode, teamBoardReqDTO);

        return ResponseUtil.successResponse(SuccessCode.TEAMBOARD_SAVE_SUCCESS, teamBoardId);
    }

    @Operation(summary = "팀 게시글 수정", description = "회원이 팀 게시판에 등록된 게시글을 수정한다.")
    @PutMapping("/{teamBoardId}")
    public ResponseEntity<ApiResponse<Void>> updateTeamBoard(
            @PathVariable Long teamBoardId,
            @RequestBody @Valid TeamBoardReqDTO teamBoardReqDTO) {

        teamBoardService.updateTeamBoard(teamBoardId, teamBoardReqDTO);

        return ResponseUtil.successResponse(SuccessCode.TEAMBOARD_UPDATE_SUCCESS);
    }

    @Operation(summary = "팀 게시글 삭제", description = "회원이 팀 게시판에 등록된 게시글을 삭제한다.")
    @DeleteMapping("/{teamBoardId}")
    public ResponseEntity<ApiResponse<Void>> deleteTeamBoard(
            @PathVariable Long teamBoardId) {

        teamBoardService.deleteTeamBoard(teamBoardId);

        return ResponseUtil.successResponse(SuccessCode.TEAMBOARD_DELETE_SUCCESS);
    }
}
