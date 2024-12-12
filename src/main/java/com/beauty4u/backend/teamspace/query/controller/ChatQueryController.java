package com.beauty4u.backend.teamspace.query.controller;

import com.beauty4u.backend.teamspace.command.domain.aggregate.ChatMessage;
import com.beauty4u.backend.teamspace.query.service.ChatQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vi")
@RequiredArgsConstructor
@Tag(name = "ChatMessage", description = "채팅 관련 API")
public class ChatQueryController {

    private final ChatQueryService chatQueryService;

    @GetMapping("/teamspace/{teamspaceId}")
    @Operation(summary = "채팅 내역 조회", description = "팀스페이스 채팅 내역을 조회한다.")
    public ResponseEntity<List<ChatMessage>> getChatHistory(@PathVariable Long teamspaceId) {

        List<ChatMessage> chatMessages = chatQueryService.getChatHistory(teamspaceId);
        return new ResponseEntity<>(chatMessages, HttpStatus.OK);

    }
}
