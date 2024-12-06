package com.beauty4u.backend.user.query.controller;

import com.beauty4u.backend.common.response.ApiResponse;
import com.beauty4u.backend.common.response.ResponseUtil;
import com.beauty4u.backend.common.success.SuccessCode;
import com.beauty4u.backend.user.query.dto.FindUserCodeReqDTO;
import com.beauty4u.backend.user.query.dto.UserListResDTO;
import com.beauty4u.backend.user.query.service.UserQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
@Tag(name = "User", description = "회원 관련 API")
public class UserQueryController {

    private final UserQueryService userQueryService;

    @Operation(summary = "회원 목록 조회")
    @GetMapping("/list")
    public ResponseEntity<ApiResponse<List<UserListResDTO>>> findUserList(
            @RequestParam(defaultValue = "1") Long page,
            @RequestParam(defaultValue = "10") Long count) {

        List<UserListResDTO> userListResDTOS = userQueryService.findUserList(page, count);

        return ResponseUtil.successResponse(SuccessCode.USER_READ_SUCCESS, userListResDTOS);
    }

    @Operation(summary = "사원번호(아이디) 찾기", description = "이름, 전화번호, 이메일을 입력하여 사번을 조회할 수 있다.")
    @PostMapping("/id/search")
    public ResponseEntity<ApiResponse<Void>> findUserCode(
            @RequestBody FindUserCodeReqDTO findUserCodeReqDTO) {

        userQueryService.findUserCode(findUserCodeReqDTO);

        return ResponseUtil.successResponse(SuccessCode.USER_READ_SUCCESS);
    }
}
