package com.beauty4u.backend.teamspace.query.controller;

import com.beauty4u.backend.teamspace.command.domain.aggregate.ChatMessage;
import com.beauty4u.backend.teamspace.query.service.ChatQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chatting")
@RequiredArgsConstructor
@Tag(name = "ChatMessage", description = "채팅 관련 API")
public class ChatQueryController {

    private final ChatQueryService chatQueryService;

    @GetMapping("/teamspace/{teamspaceId}")
    @Operation(summary = "채팅 내역 조회", description = "팀스페이스 채팅 내역을 조회한다.")
    public ResponseEntity<?> getChattingMessageByTeamspaceId(@PathVariable Long teamspaceId) {
        try {
            ChatMessage chatMessage = chatQueryService.getChattingMessageInfoByTeamspaceId(teamspaceId);

            if (chatMessage != null) {
                return ResponseEntity.ok(chatMessage);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("해당 Teamspace ID에 해당하는 메시지가 없습니다.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("데이터 조회 중 오류가 발생했습니다: " + e.getMessage());
        }
    }
}
