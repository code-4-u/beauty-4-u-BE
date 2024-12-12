package com.beauty4u.backend.user.query.controller;

import com.beauty4u.backend.common.response.ApiResponse;
import com.beauty4u.backend.common.response.ResponseUtil;
import com.beauty4u.backend.common.success.SuccessCode;
import com.beauty4u.backend.common.util.CustomUserUtil;
import com.beauty4u.backend.user.query.dto.*;
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

    @Operation(summary = "회원 목록 조회")
    @GetMapping("/list")
    public ResponseEntity<ApiResponse<List<UserListResDTO>>> findUserList(
            @RequestParam(defaultValue = "1") Long page,
            @RequestParam(defaultValue = "10") Long count) {

        List<UserListResDTO> userListResDTOS = userQueryService.findUserList(page, count);

        return ResponseUtil.successResponse(SuccessCode.USER_FIND_LIST_SUCCESS, userListResDTOS);
    }

    @Operation(summary = "내 정보 조회", description = "회원을 상세 조회한다.")
    @GetMapping("/profile")
    public ResponseEntity<ApiResponse<FindUserDetailResDTO>> findUserDetail() {

        String loginUserCode = CustomUserUtil.getCurrentUserCode();
        FindUserDetailResDTO findUserDetailResDTO = userQueryService.findUserDetail(loginUserCode);

        return ResponseUtil.successResponse(SuccessCode.USER_FIND_DETAIL_SUCCESS, findUserDetailResDTO);
    }

    @Operation(summary = "부서 목록 조회")
    @GetMapping("/dept/list")
    public ResponseEntity<ApiResponse<List<DeptResDTO>>> findDeptList() {

        List<DeptResDTO> deptResDTOS = userQueryService.findDeptList();

        return ResponseUtil.successResponse(SuccessCode.DEPT_READ_SUCCESS, deptResDTOS);
    }

    @Operation(summary = "직급 목록 조회")
    @GetMapping("/job/list")
    public ResponseEntity<ApiResponse<List<JobResDTO>>> findJobList() {

        List<JobResDTO> jobResDTOS = userQueryService.findJobList();

        return ResponseUtil.successResponse(SuccessCode.JOB_READ_SUCCESS, jobResDTOS);
    }

    @Operation(summary = "권한 목록 조회")
    @GetMapping("/role/list")
    public ResponseEntity<ApiResponse<List<RoleResDTO>>> findRoleList() {

        List<RoleResDTO> roleResDTOS = userQueryService.findRoleList();

        return ResponseUtil.successResponse(SuccessCode.ROLE_READ_SUCCESS, roleResDTOS);
    }
}
