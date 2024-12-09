package com.beauty4u.backend.teamspace.query.controller;

import com.beauty4u.backend.teamspace.query.dto.TeamSpaceResponse;
import com.beauty4u.backend.teamspace.query.service.TeamSpaceQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/vi/teamspace")
@RequiredArgsConstructor
@Tag(name = "TeamSpace", description = "팀스페이스 API")
public class TeamSpaceQueryController {

    private final TeamSpaceQueryService teamSpaceQueryService;

    @GetMapping("/dept")
    @Operation(summary = "팀스페이스 조회", description = "팀스페이스 정보를 조회한다.")
    public TeamSpaceResponse getTeamspaceByDeptCode(@RequestParam String deptCode) {
        // 서비스에서 DTO를 생성 및 반환
        return teamSpaceQueryService.getTeamSpaceByDeptCode(deptCode);
    }
}
