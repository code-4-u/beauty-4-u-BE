package com.beauty4u.backend.noti.command.application.controller;

import com.beauty4u.backend.common.util.CustomUserUtil;
import com.beauty4u.backend.noti.command.application.service.NotiService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/noti")
@Tag(name = "Notification", description = "알림 관련 API")
public class NotiCommandController {

    private final NotiService notiService;

    @Operation(summary = "SSE 연결", description = "SSE를 연결한다.")
    @GetMapping(value = "/connect", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public ResponseEntity<SseEmitter> subscribe(
            @RequestHeader(value = "Last-Event-ID", required = false, defaultValue = "") String lastEventId) {

        String loginUserCode = CustomUserUtil.getCurrentUserCode();

        SseEmitter sseEmitter = notiService.subscribe(loginUserCode, lastEventId);

        return ResponseEntity.ok(sseEmitter);
    }
}
