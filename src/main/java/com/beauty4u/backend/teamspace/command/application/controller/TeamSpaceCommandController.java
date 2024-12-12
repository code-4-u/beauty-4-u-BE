package com.beauty4u.backend.teamspace.command.application.controller;

import com.beauty4u.backend.teamspace.command.application.service.TeamSpaceService;
import com.beauty4u.backend.teamspace.command.domain.aggregate.Teamspace;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/teamspace")
@RequiredArgsConstructor
@Tag(name = "TeamSpace", description = "팀스페이스 관련 API")
public class TeamSpaceCommandController {

    private final TeamSpaceService teamSpaceService;

    @PostMapping("/create")
    @Operation(summary = "팀스페이스 생성", description = "팀스페이스를 생성한다.")
    public ResponseEntity<Void> createTeamSpace(@RequestParam String deptCode) {
        Teamspace teamspace =  teamSpaceService.createTeamspace(deptCode);
        return ResponseEntity.status(HttpStatus.CREATED).build();  // 본문 없이 상태 코드만 반환
    }

}
