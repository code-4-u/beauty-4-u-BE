package com.beauty4u.backend.teamspace.query.controller;

import com.beauty4u.backend.teamspace.query.dto.chatMessage.ChatMessageResDto;
import com.beauty4u.backend.teamspace.query.service.ChatQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Tag(name = "ChatMessage", description = "채팅 관련 API")
public class ChatQueryController {

    private final ChatQueryService chatQueryService;

    @GetMapping("/chat/{chatRoomId}")
    @Operation(summary = "채팅 내역 조회", description = "채팅방 채팅 내역을 조회한다.")
    public ResponseEntity<List<ChatMessageResDto>> getChatHistory(@PathVariable Long chatRoomId) {

        List<ChatMessageResDto> chatMessages = chatQueryService.getChatHistory(chatRoomId);
        return new ResponseEntity<>(chatMessages, HttpStatus.OK);
    }
}
