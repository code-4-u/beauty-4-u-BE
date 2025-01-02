package com.beauty4u.backend.user.command.application.controller;

import com.beauty4u.backend.common.exception.CustomException;
import com.beauty4u.backend.common.exception.ErrorCode;
import com.beauty4u.backend.common.response.ApiResponse;
import com.beauty4u.backend.common.response.ResponseUtil;
import com.beauty4u.backend.common.success.SuccessCode;
import com.beauty4u.backend.common.util.CustomUserUtil;
import com.beauty4u.backend.security.util.JwtUtil;
import com.beauty4u.backend.user.command.application.dto.*;
import com.beauty4u.backend.user.command.application.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
@Tag(name = "User", description = "회원 관련 API")
public class UserCommandController {

    private static final String ACCESS_TOKEN_HEADER = "Authorization";
    private static final String REFRESH_TOKEN_HEADER = "Refresh-Token";

    private final UserService userService;
    private final JwtUtil jwtUtil;

    @Operation(summary = "회원 등록", description = "관리자가 신규 회원을 등록한다.")
    @PostMapping
    public ResponseEntity<ApiResponse<Void>> saveUser(
            @RequestBody @Valid CreateUserReqDTO createUserReqDTO) {

        userService.saveUser(createUserReqDTO);

        return ResponseUtil.successResponse(SuccessCode.USER_SAVE_SUCCESS);
    }

    @Operation(summary = "로그인", description = "등록된 회원이 로그인을 한다.")
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<Void>> loginUser(
            @RequestBody @Valid LoginUserReqDTO loginUserReqDTO,
            HttpServletResponse response) {

        Authentication authentication = userService.loginUser(loginUserReqDTO);

        String accessToken = jwtUtil.generateAccessToken(authentication);
        String refreshToken = jwtUtil.generateRefreshToken(authentication.getName());

        response.setHeader(ACCESS_TOKEN_HEADER, accessToken);
        response.setHeader(REFRESH_TOKEN_HEADER, refreshToken);

        return ResponseUtil.successResponse(SuccessCode.USER_LOGIN_SUCCESS);
    }

    @Operation(summary = "로그아웃", description = "현재 로그인 된 회원이 로그아웃을 한다.")
    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<Void>> logoutUser(
            HttpServletRequest request) {

        try {
            String userCode = CustomUserUtil.getCurrentUserCode();
            String accessToken = request.getHeader("Authorization")
                    .substring(7);

            userService.logoutUser(userCode, accessToken);
            return ResponseUtil.successResponse(SuccessCode.USER_LOGOUT_SUCCESS);
        } catch (Exception e) {
            throw new CustomException(ErrorCode.LOGOUT_FAIL);
        }
    }

    @Operation(summary = "사원번호(아이디) 찾기", description = "이름, 전화번호, 이메일을 입력하여 사번을 조회할 수 있다.")
    @PostMapping("/id/search")
    public ResponseEntity<ApiResponse<Void>> findUserCode(
            @RequestBody FindUserCodeReqDTO findUserCodeReqDTO) {

        userService.findUserCode(findUserCodeReqDTO);

        return ResponseUtil.successResponse(SuccessCode.USERCODE_FIND_SUCCESS);
    }

    @Operation(summary = "비밀번호 재발급", description = "사번, 이름, 이메일을 입력하여 비밀번호를 재발급 할 수 있다.")
    @PostMapping("/password/reset")
    public ResponseEntity<ApiResponse<Void>> resetUserPassword(
            @RequestBody ResetUserPasswordReqDTO resetUserPasswordReqDTO) {

        userService.resetUserPassword(resetUserPasswordReqDTO);

        return ResponseUtil.successResponse(SuccessCode.PASSWORD_RESET_SUCCESS);
    }

    @Operation(summary = "비밀번호 수정", description = "현재 로그인 된 회원이 비밀번호를 수정한다.")
    @PutMapping("/password")
    public ResponseEntity<ApiResponse<Void>> updateUserPassword(
            @RequestBody UpdateUserPasswordReqDTO updateUserPasswordReqDTO) {

        String loginUserCode = CustomUserUtil.getCurrentUserCode();
        userService.updateUserPassword(loginUserCode, updateUserPasswordReqDTO);

        return ResponseUtil.successResponse(SuccessCode.PASSWORD_UPDATE_SUCCESS);
    }

    @Operation(summary = "비밀번호 초기화", description = "관리자가 회원의 비밀번호를 초기화한다.")
    @PutMapping("/password/admin/reset")
    public ResponseEntity<ApiResponse<Void>> adminResetUserPassword(
            @RequestBody UserCodeReqDTO userCodeReqDTO) {

        userService.adminResetUserPassword(userCodeReqDTO);

        return ResponseUtil.successResponse(SuccessCode.PASSWORD_UPDATE_SUCCESS);
    }

    @Operation(summary = "회원 비활성화", description = "관리자가 회원을 비활성화(계정 만료)한다.")
    @PutMapping("/expire")
    public ResponseEntity<ApiResponse<Void>> expireUser(
            @RequestBody UserCodeReqDTO userCodeReqDTO) {

        userService.expireUser(userCodeReqDTO);

        return ResponseUtil.successResponse(SuccessCode.EXPIRE_UPDATE_SUCCESS);
    }

    @Operation(summary = "회원 활성화", description = "관리자가 비활성화 된 회원을 다시 활성화한다.")
    @PutMapping("/unexpire")
    public ResponseEntity<ApiResponse<Void>> unexpireUser(
            @RequestBody UserCodeReqDTO userCodeReqDTO) {

        userService.unexpireUser(userCodeReqDTO);

        return ResponseUtil.successResponse(SuccessCode.EXPIRE_UPDATE_SUCCESS);
    }

    @Operation(summary = "회원 정보 수정", description = "관리자가 등록된 회원의 정보를 수정한다.")
    @PutMapping("/{userCode}")
    public ResponseEntity<ApiResponse<Void>> updateUser(
            @PathVariable String userCode,
            @RequestBody UpdateUserReqDTO updateUserReqDTO) {

        userService.updateUser(userCode, updateUserReqDTO);

        return ResponseUtil.successResponse(SuccessCode.USER_UPDATE_SUCCESS);
    }
}