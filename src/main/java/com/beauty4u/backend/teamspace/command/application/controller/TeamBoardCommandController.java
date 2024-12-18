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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/teamspace")
@RequiredArgsConstructor
@Tag(name = "TeamSpace", description = "팀스페이스 관련 API")
public class TeamBoardCommandController {

    private final TeamBoardService teamBoardService;

    @Operation(summary = "팀 게시글 등록", description = "회원(직원)이 팀 게시판에 게시글을 등록한다.")
    @PostMapping
    public ResponseEntity<ApiResponse<Long>> saveTeamBoard(
            @RequestBody @Valid TeamBoardReqDTO teamBoardReqDTO) {

        String loginUserCode = CustomUserUtil.getCurrentUserCode();

        Long teamBoardId = teamBoardService.saveTeamBoard(loginUserCode, teamBoardReqDTO);

        return ResponseUtil.successResponse(SuccessCode.TEAMBOARD_SAVE_SUCCESS, teamBoardId);
    }
}
