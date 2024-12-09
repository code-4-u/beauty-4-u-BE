package com.beauty4u.backend.teamspace.command.application.controller;

import com.beauty4u.backend.teamspace.command.application.service.TeamSpaceCommandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/teamspaces")
@RequiredArgsConstructor
@Tag(name = "TeamSpace", description = "팀스페이스 API")
public class TeamSpaceCommandController {

    private final TeamSpaceCommandService teamSpaceCommandService;

    @PostMapping
    @Operation(summary = "팀스페이스 생성", description = "팀스페이스를 생성한다.")
    public ResponseEntity<Void> createTeamspace(@RequestParam String deptCode) {
        teamSpaceCommandService.createTeamspace(deptCode);
        return ResponseEntity.status(HttpStatus.CREATED).build();  // 본문 없이 상태 코드만 반환
    }
}
