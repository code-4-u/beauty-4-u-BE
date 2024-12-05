package com.beauty4u.backend.user.query.controller;

import com.beauty4u.backend.common.response.ApiResponse;
import com.beauty4u.backend.common.success.CustomSuccessHandler;
import com.beauty4u.backend.common.success.SuccessCode;
import com.beauty4u.backend.user.query.dto.UserListResDTO;
import com.beauty4u.backend.user.query.service.UserQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
@Tag(name = "User", description = "회원 관련 API")
public class UserQueryController {

    private final UserQueryService userQueryService;
    private final CustomSuccessHandler customSuccessHandler;

    @Operation(summary = "회원 목록 조회")
    @GetMapping("/list")
    public ResponseEntity<ApiResponse<List<UserListResDTO>>> findUserList(
            @RequestParam(defaultValue = "1") Long page,
            @RequestParam(defaultValue = "10") Long count) {

        List<UserListResDTO> userListResDTOS = userQueryService.findUserList(page, count);

        return customSuccessHandler.createSuccessResponse(SuccessCode.USER_READ_SUCCESS, userListResDTOS);
    }
}
