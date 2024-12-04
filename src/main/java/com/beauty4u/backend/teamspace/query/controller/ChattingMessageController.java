package com.beauty4u.backend.teamspace.query.controller;

import com.beauty4u.backend.teamspace.command.domain.aggregate.ChattingMessage;
import com.beauty4u.backend.teamspace.query.service.ChattingMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chatting")
@RequiredArgsConstructor
public class ChattingMessageController {

    private final ChattingMessageService chattingMessageService;

    @GetMapping("/teamspace/{teamspaceId}")
    public ResponseEntity<?> getChattingMessageByTeamspaceId(@PathVariable Long teamspaceId) {
        try {
            ChattingMessage chattingMessage = chattingMessageService.getChattingMessageInfoByTeamspaceId(teamspaceId);

            if (chattingMessage != null) {
                return ResponseEntity.ok(chattingMessage);
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
