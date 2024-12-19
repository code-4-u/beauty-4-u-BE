package com.beauty4u.backend.teamspace.command.application.controller;

import com.beauty4u.backend.common.response.ApiResponse;
import com.beauty4u.backend.common.response.ResponseUtil;
import com.beauty4u.backend.common.success.SuccessCode;
import com.beauty4u.backend.teamspace.command.application.service.TeamSpaceService;
import com.beauty4u.backend.teamspace.command.domain.aggregate.Teamspace;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/teamspace")
@RequiredArgsConstructor
@Tag(name = "TeamSpace", description = "팀스페이스 관련 API")
public class TeamSpaceCommandController {

    private final TeamSpaceService teamSpaceService;

    @PostMapping("/create")
    @Operation(summary = "팀스페이스 생성", description = "팀스페이스를 생성한다.")
    public ResponseEntity<ApiResponse<Void>> createTeamSpace(@RequestParam String deptCode) {
        Teamspace teamspace =  teamSpaceService.createTeamspace(deptCode);
        return ResponseUtil.successResponse(SuccessCode.TEAMSPACE_SAVE_SUCCESS);
    }

    @PostMapping("/initialize")
    @Operation(summary = "부서별 팀스페이스 생성", description = "부서별 초기 팀스페이스를 생성한다.")
    public ResponseEntity<ApiResponse<Void>> initializeCreateTeamSpace() {
        List<Teamspace> teamspaces = teamSpaceService.createAndAssignTeamSpaces();
        return ResponseUtil.successResponse(SuccessCode.TEAMSPACE_SAVE_ALL_SUCCESS);
    }
}
