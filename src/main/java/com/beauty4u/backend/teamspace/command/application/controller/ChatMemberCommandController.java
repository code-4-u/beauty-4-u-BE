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

import java.util.List;

@RestController
@RequestMapping("/api/v1/chat")
@RequiredArgsConstructor
@Tag(name = "ChatMember", description = "채팅방 멤버 관련 API")
public class ChatMemberCommandController {

    private final ChatMemberService chatMemberService;

    // 특정 채팅방에 멤버를 추가한다.
    @PostMapping("/{chatRoomId}/invite")
    @Operation(summary = "채팅방 멤버 초대", description = "채팅방에 채팅 멤버를 추가한다.")
    public ResponseEntity<ApiResponse<Void>> invite(@PathVariable Long chatRoomId,
                                                    @RequestBody List<String> userCodes) {
        String loginUserCode = CustomUserUtil.getCurrentUserCode();
        chatMemberService.saveChatMember(loginUserCode, chatRoomId, userCodes);
        return ResponseUtil.successResponse(SuccessCode.CHATMEMBER_SAVE_SUCCESS);
    }

    @DeleteMapping("/{chatRoomId}/leave")
    @Operation(summary = "채팅방 나가기", description = "채팅방에서 나간다.")
    public ResponseEntity<ApiResponse<Void>> leaveChatRoom(@PathVariable Long chatRoomId) {

        String loginUserCode = CustomUserUtil.getCurrentUserCode();
        chatMemberService.leaveChatRoom(chatRoomId, loginUserCode); // 채팅방 나가기 처리 로직 호출
        return ResponseUtil.successResponse(SuccessCode.CHATMEMBER_DELETE_SUCCESS);
    }
}
