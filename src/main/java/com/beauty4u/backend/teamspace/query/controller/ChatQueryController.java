package com.beauty4u.backend.teamspace.query.controller;

import com.beauty4u.backend.common.response.ApiResponse;
import com.beauty4u.backend.common.response.ResponseUtil;
import com.beauty4u.backend.common.success.SuccessCode;
import com.beauty4u.backend.teamspace.query.dto.chatMessage.ChatMessageResDto;
import com.beauty4u.backend.teamspace.query.service.ChatQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Tag(name = "ChatMessage", description = "채팅 관련 API")
public class ChatQueryController {

    private final ChatQueryService chatQueryService;

    @GetMapping("/chat/{chatRoomId}")
    @Operation(summary = "채팅 내역 조회", description = "채팅방 채팅 내역을 조회한다.")
    public ResponseEntity<ApiResponse<List<ChatMessageResDto>>> getChatHistory(@PathVariable Long chatRoomId) {

        List<ChatMessageResDto> chatMessages = chatQueryService.getChatHistory(chatRoomId);

        return ResponseUtil.successResponse(SuccessCode.CHAT_LIST_FIND_SUCCESS, chatMessages);
    }
}
