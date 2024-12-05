package com.beauty4u.backend.user.command.application.controller;

import com.beauty4u.backend.common.exception.CustomException;
import com.beauty4u.backend.common.exception.ErrorCode;
import com.beauty4u.backend.common.response.ApiResponse;
import com.beauty4u.backend.common.response.ResponseUtil;
import com.beauty4u.backend.common.success.SuccessCode;
import com.beauty4u.backend.common.util.CustomUserUtil;
import com.beauty4u.backend.user.command.application.dto.CreateUserRequest;
import com.beauty4u.backend.user.command.application.service.UserCommandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
@Tag(name = "User", description = "회원 관련 API")
public class UserCommandController {

    private final UserCommandService userCommandService;

    @Operation(summary = "회원 등록", description = "관리자가 신규 회원을 등록한다.")
    @PostMapping
    public ResponseEntity<ApiResponse<Void>> saveUser(
            @RequestBody @Valid CreateUserRequest createUserRequest) {

        userCommandService.saveUser(createUserRequest);

        return ResponseUtil.successResponse(SuccessCode.USER_REGISTER_SUCCESS);
    }

    @Operation(summary = "로그아웃", description = "현재 로그인 된 회원이 로그아웃 한다.")
    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<Void>> logoutUser(HttpServletRequest request) {

        String userCode = CustomUserUtil.getCurrentUserCode();
        String accessToken = request.getHeader("Authorization")
                                    .substring(7);

        if (!userCode.isEmpty()) {
            userCommandService.logoutUser(userCode, accessToken);
            SecurityContextHolder.clearContext();
            return ResponseUtil.successResponse(SuccessCode.USER_LOGOUT_SUCCESS);
        } else {
            throw new CustomException(ErrorCode.LOGOUT_FAIL);
        }
    }
}