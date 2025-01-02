package com.beauty4u.backend.noti.command.application.controller;

import com.beauty4u.backend.common.response.ApiResponse;
import com.beauty4u.backend.common.response.ResponseUtil;
import com.beauty4u.backend.common.success.SuccessCode;
import com.beauty4u.backend.common.util.CustomUserUtil;
import com.beauty4u.backend.noti.command.application.dto.NotiIdReqDTO;
import com.beauty4u.backend.noti.command.application.dto.NotiIdReqDTOList;
import com.beauty4u.backend.noti.command.application.service.NotiService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @Operation(summary = "알림 읽음", description = "알림의 읽음 상태를 읽음으로 수정한다.")
    @PutMapping("/{notiId}")
    public ResponseEntity<ApiResponse<Void>> updateNotiRead(
            @RequestBody NotiIdReqDTO notiIdReqDTO) {

        notiService.updateNotiRead(notiIdReqDTO);

        return ResponseUtil.successResponse(SuccessCode.NOTI_READ_UPDATE_SUCCESS);
    }

    @Operation(summary = "알림 모두 읽음", description = "알림을 한번에 모두 읽음 처리한다.")
    @PutMapping("/all")
    public ResponseEntity<ApiResponse<Void>> updateNotiReadAll(
            @RequestBody NotiIdReqDTOList notiIdReqDTOList
    ) {

        notiService.updateNotiListRead(notiIdReqDTOList);

        return ResponseUtil.successResponse(SuccessCode.NOTI_READ_All_UPDATE_SUCCESS);
    }
}
