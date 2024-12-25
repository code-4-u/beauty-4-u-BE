package com.beauty4u.backend.teamspace.query.controller;

import com.beauty4u.backend.common.util.CustomUserUtil;
import com.beauty4u.backend.teamspace.query.dto.chatroom.ChatRoomDetailsDto;
import com.beauty4u.backend.teamspace.query.dto.chatroom.ChatRoomUserInfoDto;
import com.beauty4u.backend.teamspace.query.service.ChatRoomQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/chat")
@RequiredArgsConstructor
@Tag(name = "ChatRoom", description = "채팅방 API")
public class ChatRoomQueryController {

    private final ChatRoomQueryService chatRoomQueryService;


    @Operation(summary = "채팅방 내의 채팅 참여자 정보 조회", description = "부서 코드에 맞는 채팅방 참여자의 정보를 조회합니다.")
    @GetMapping("/{chatRoomId}/users")
    public List<ChatRoomUserInfoDto> getChatMemberByChatRoomId(
            @PathVariable Long chatRoomId) {

        String loginUserCode = CustomUserUtil.getCurrentUserCode();
        return chatRoomQueryService.findAllChatRoomUser(chatRoomId, loginUserCode);
    }


    // 팀스페이스 통합 컨트롤러
    @Operation(summary = "채팅방 채팅 상세조회", description = "채팅방 채팅방 참여자, 채팅 목록을 조회합니다.")
    @GetMapping("/{chatRoomId}/details")
    public ChatRoomDetailsDto getChatDetails(@PathVariable Long chatRoomId) {

        String loginUserCode = CustomUserUtil.getCurrentUserCode();
        return chatRoomQueryService.getChatRoomDetails(chatRoomId, loginUserCode);
    }
}
