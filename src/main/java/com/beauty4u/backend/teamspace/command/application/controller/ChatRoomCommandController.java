package com.beauty4u.backend.teamspace.command.application.controller;

import com.beauty4u.backend.common.response.ApiResponse;
import com.beauty4u.backend.common.response.ResponseUtil;
import com.beauty4u.backend.common.success.SuccessCode;
    import com.beauty4u.backend.common.util.CustomUserUtil;
import com.beauty4u.backend.teamspace.command.application.dto.chatroom.ChatRoomResponseDto;
import com.beauty4u.backend.teamspace.command.application.dto.chatroom.InviteUsersToChatRoomReqDto;
import com.beauty4u.backend.teamspace.command.application.service.ChatRoomService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/chat")
@RequiredArgsConstructor
@Tag(name = "ChatRoom", description = "채팅방 관련 API")
public class ChatRoomCommandController {

    private final ChatRoomService chatRoomService;

    @PostMapping("/create")
    @Operation(summary = "채팅방 생성", description = "채팅방을 생성한다.")
    public ResponseEntity<ApiResponse<ChatRoomResponseDto>> createTeamSpace(@RequestBody InviteUsersToChatRoomReqDto reqDto) {

        String loginUserCode = CustomUserUtil.getCurrentUserCode();

        ChatRoomResponseDto chatRoomResponseDto = chatRoomService.createChatRoom(loginUserCode, reqDto);

        return ResponseUtil.successResponse(SuccessCode.CHATROOM_SAVE_SUCCESS, chatRoomResponseDto);
    }
}
