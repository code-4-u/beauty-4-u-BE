package com.beauty4u.backend.teamspace.query.controller;

import com.beauty4u.backend.common.response.ApiResponse;
import com.beauty4u.backend.common.response.ResponseUtil;
import com.beauty4u.backend.common.success.SuccessCode;
import com.beauty4u.backend.common.util.CustomUserUtil;
import com.beauty4u.backend.teamspace.query.dto.chatroom.ChatRoomDTO;
import com.beauty4u.backend.teamspace.query.dto.chatroom.ChatRoomDetailsDto;
import com.beauty4u.backend.teamspace.query.dto.chatroom.ChatRoomUserInfoDto;
import com.beauty4u.backend.teamspace.query.service.ChatRoomQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/chat")
@RequiredArgsConstructor
@Tag(name = "ChatRoom", description = "채팅방 API")
public class ChatRoomQueryController {

    private final ChatRoomQueryService chatRoomQueryService;

    @Operation(summary = "채팅방 리스트 조회", description = "본인이 속한 채팅방 리스트를 조회합니다.")
    @GetMapping("/rooms")
    public ResponseEntity<ApiResponse<List<ChatRoomDTO>>> getMyChatRooms() {

        String loginUserCode = CustomUserUtil.getCurrentUserCode();
        List<ChatRoomDTO> chatRooms = chatRoomQueryService.getMyChatRooms(loginUserCode);

        return ResponseUtil.successResponse(SuccessCode.CHAT_ROOM_LIST_FIND_SUCCESS, chatRooms);
    }

    @Operation(summary = "채팅방 내의 채팅 참여자 정보 조회", description = "부서 코드에 맞는 채팅방 참여자의 정보를 조회합니다.")
    @GetMapping("/{chatRoomId}/users")
    public ResponseEntity<ApiResponse<List<ChatRoomUserInfoDto>>> getChatMemberByChatRoomId(
            @PathVariable Long chatRoomId) {

        String loginUserCode = CustomUserUtil.getCurrentUserCode();

        List<ChatRoomUserInfoDto> allChatRoomUser = chatRoomQueryService.findAllChatRoomUser(chatRoomId, loginUserCode);

        return ResponseUtil.successResponse(SuccessCode.CHAT_ROOM_USER_LIST_FIND_SUCCESS, allChatRoomUser);
    }


    // 팀스페이스 통합 컨트롤러
    @Operation(summary = "채팅방 채팅 상세조회", description = "채팅방 채팅방 참여자, 채팅 목록을 조회합니다.")
    @GetMapping("/{chatRoomId}/details")
    public ResponseEntity<ApiResponse<ChatRoomDetailsDto>> getChatDetails(@PathVariable Long chatRoomId) {

        String loginUserCode = CustomUserUtil.getCurrentUserCode();

        ChatRoomDetailsDto chatRoomDetails = chatRoomQueryService.getChatRoomDetails(chatRoomId, loginUserCode);

        return ResponseUtil.successResponse(SuccessCode.CHAT_ROOM_DETAIL_FIND_SUCCESS, chatRoomDetails);
    }
}
