package com.beauty4u.backend.teamspace.command.application.controller;

import com.beauty4u.backend.common.response.ApiResponse;
import com.beauty4u.backend.common.response.ResponseUtil;
import com.beauty4u.backend.common.success.SuccessCode;
import com.beauty4u.backend.common.util.CustomUserUtil;
import com.beauty4u.backend.teamspace.command.application.dto.teamboard.TeamBoardReplyReqDTO;
import com.beauty4u.backend.teamspace.command.application.service.TeamBoardReplyService;
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
public class TeamBoardReplyCommandController {

    private final TeamBoardReplyService teamBoardReplyService;

    @Operation(summary = "팀 게시판 댓글 등록", description = "회원이 팀 게시판 댓글을 등록한다.")
    @PostMapping("/{teamBoardId}/reply")
    public ResponseEntity<ApiResponse<Void>> saveTeamBoardReply(
            @PathVariable Long teamBoardId,
            @RequestBody @Valid TeamBoardReplyReqDTO teamBoardReplyReqDTO) {

        String loginUserCode = CustomUserUtil.getCurrentUserCode();

        teamBoardReplyService.saveTeamBoardReply(loginUserCode, teamBoardId, teamBoardReplyReqDTO);

        return ResponseUtil.successResponse(SuccessCode.TEAMBOARD_REPLY_SAVE_SUCCESS);
    }

    @Operation(summary = "팀 게시판 댓글 수정", description = "회원이 팀 게시판 댓글을 수정한다.")
    @PutMapping("/{teamBoardId}/reply/{teamBoardReplyId}")
    public ResponseEntity<ApiResponse<Void>> updateQnaReply(
            @PathVariable Long teamBoardId,
            @PathVariable Long teamBoardReplyId,
            @RequestBody @Valid TeamBoardReplyReqDTO teamBoardReplyReqDTO) {

        teamBoardReplyService.updateTeamBoardReply(teamBoardId, teamBoardReplyId, teamBoardReplyReqDTO);

        return ResponseUtil.successResponse(SuccessCode.TEAMBOARD_REPLY_UPDATE_SUCCESS);
    }
}
