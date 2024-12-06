package com.beauty4u.backend.user.command.application.controller;

import com.beauty4u.backend.common.exception.CustomException;
import com.beauty4u.backend.common.exception.ErrorCode;
import com.beauty4u.backend.common.response.ApiResponse;
import com.beauty4u.backend.common.response.ResponseUtil;
import com.beauty4u.backend.common.success.SuccessCode;
import com.beauty4u.backend.common.util.CustomUserUtil;
import com.beauty4u.backend.security.util.JwtUtil;
import com.beauty4u.backend.user.command.application.dto.CreateUserRequest;
import com.beauty4u.backend.user.command.application.dto.LoginUserReqDTO;
import com.beauty4u.backend.user.command.application.service.UserCommandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
@Tag(name = "User", description = "회원 관련 API")
public class UserCommandController {

    private static final String ACCESS_TOKEN_HEADER = "Authorization";
    private static final String REFRESH_TOKEN_HEADER = "Refresh-Token";

    private final UserCommandService userCommandService;
    private final JwtUtil jwtUtil;

    @Operation(summary = "회원 등록", description = "관리자가 신규 회원을 등록한다.")
    @PostMapping
    public ResponseEntity<ApiResponse<Void>> saveUser(
            @RequestBody @Valid CreateUserRequest createUserRequest) {

        userCommandService.saveUser(createUserRequest);

        return ResponseUtil.successResponse(SuccessCode.USER_REGISTER_SUCCESS);
    }

    @Operation(summary = "로그인", description = "등록된 회원이 로그인을 한다.")
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<Void>> loginUser(
            @RequestBody @Valid LoginUserReqDTO loginUserReqDTO,
            HttpServletResponse response) {

        Authentication authentication = userCommandService.loginUser(loginUserReqDTO);

        String accessToken = jwtUtil.generateAccessToken(authentication);
        String refreshToken = jwtUtil.generateRefreshToken(authentication.getName());

        response.setHeader(ACCESS_TOKEN_HEADER, accessToken);
        response.setHeader(REFRESH_TOKEN_HEADER, refreshToken);

        return ResponseUtil.successResponse(SuccessCode.USER_LOGIN_SUCCESS);
    }

    @Operation(summary = "로그아웃", description = "현재 로그인 된 회원이 로그아웃을 한다.")
    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<Void>> logoutUser(
            HttpServletRequest request,
            HttpServletResponse response) {

        try {
            String userCode = CustomUserUtil.getCurrentUserCode();
            String accessToken = request.getHeader("Authorization")
                    .substring(7);

            userCommandService.logoutUser(userCode, accessToken);
            return ResponseUtil.successResponse(SuccessCode.USER_LOGOUT_SUCCESS);
        } catch (Exception e) {
            throw new CustomException(ErrorCode.LOGOUT_FAIL);
        }
    }
}