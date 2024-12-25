package com.beauty4u.backend.teamspace.command.application.controller;

import com.beauty4u.backend.common.response.ApiResponse;
import com.beauty4u.backend.common.response.ResponseUtil;
import com.beauty4u.backend.common.success.SuccessCode;
import com.beauty4u.backend.common.util.CustomUserUtil;
import com.beauty4u.backend.teamspace.command.application.service.ChatMemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/chat")
@RequiredArgsConstructor
@Tag(name = "ChatMember", description = "채팅방 멤버 관련 API")
public class ChatMemberCommandController {

    private final ChatMemberService chatMemberService;

    // 특정 채팅방에 멤버를 추가한다.
    @PostMapping("/{chatRoomId}/invite/{userCode}")
    @Operation(summary = "채팅방 멤버 초대", description = "채팅방에 채팅 멤버를 추가한다.")
    public ResponseEntity<ApiResponse<Void>> invite(@PathVariable Long chatRoomId,
                                                    @PathVariable String userCode) {

        String loginUserCode = CustomUserUtil.getCurrentUserCode();
        chatMemberService.inviteUsersToChatRoom(loginUserCode, chatRoomId, userCode);
        return ResponseUtil.successResponse(SuccessCode.CHATROOM_SAVE_SUCCESS);
    }
}
