package com.beauty4u.backend.teamspace.query.controller;

import com.beauty4u.backend.teamspace.query.dto.teamspace.TeamSpaceUserInfoDto;
import com.beauty4u.backend.teamspace.query.service.TeamSpaceQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vi/teamspace")
@RequiredArgsConstructor
@Tag(name = "TeamSpace", description = "팀스페이스 API")
public class TeamSpaceQueryController {

    private final TeamSpaceQueryService teamSpaceQueryService;

    @Operation(summary = "팀스페이스 내의 채팅 참여자 정보 조회", description = "부서 코드에 맞는 팀스페이스 참여자의 정보를 조회합니다.")
    @GetMapping("/users")
    public List<TeamSpaceUserInfoDto> getTeamSpaceUsersByDeptCode(
            @RequestParam("deptCode") String deptCode) {
        return teamSpaceQueryService.findAllTeamSpaceUser(deptCode);
    }

    @Operation(summary = "팀스페이스 ID로 부서 코드 조회", description = "팀스페이스 ID에 맞는 부서 코드를 조회합니다.")
    @GetMapping("/{teamspaceId}/dept")
    public String getDeptCodeByTeamspaceId(@PathVariable String teamspaceId) {
        return teamSpaceQueryService.getDeptCodeByTeamspaceId(teamspaceId);
    }
}
