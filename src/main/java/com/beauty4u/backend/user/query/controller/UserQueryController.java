package com.beauty4u.backend.user.query.controller;

import com.beauty4u.backend.common.response.ApiResponse;
import com.beauty4u.backend.common.response.ResponseUtil;
import com.beauty4u.backend.common.success.SuccessCode;
import com.beauty4u.backend.common.util.CustomUserUtil;
import com.beauty4u.backend.user.query.dto.dept.DeptResDTO;
import com.beauty4u.backend.user.query.dto.job.JobResDTO;
import com.beauty4u.backend.user.query.dto.role.RoleResDTO;
import com.beauty4u.backend.user.query.dto.user.FindUserDetailResDTO;
import com.beauty4u.backend.user.query.dto.user.UserListResDTO;
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
    public ResponseEntity<ApiResponse<UserListResDTO>> findUserList(
            @RequestParam(defaultValue = "1") Long page,
            @RequestParam(defaultValue = "10") Long count,
            @RequestParam(defaultValue = "") String search
    ) {

        UserListResDTO userListResDTOS = userQueryService.findUserList(page, count, search);

        return ResponseUtil.successResponse(SuccessCode.USER_FIND_LIST_SUCCESS, userListResDTOS);
    }

    @Operation(summary = "내 정보 조회", description = "로그인된 회원을 상세 조회한다.")
    @GetMapping("/profile")
    public ResponseEntity<ApiResponse<FindUserDetailResDTO>> findUserDetail() {

        String loginUserCode = CustomUserUtil.getCurrentUserCode();
        FindUserDetailResDTO findUserDetailResDTO = userQueryService.findUserDetail(loginUserCode);

        return ResponseUtil.successResponse(SuccessCode.USER_FIND_DETAIL_SUCCESS, findUserDetailResDTO);
    }

    @Operation(summary = "특정 회원 정보 조회", description = "특정 회원을 상세 조회한다.")
    @GetMapping("/{userCode}")
    public ResponseEntity<ApiResponse<FindUserDetailResDTO>> findUserDetail(
            @PathVariable String userCode) {

        FindUserDetailResDTO findUserDetailResDTO = userQueryService.findUserDetail(userCode);

        return ResponseUtil.successResponse(SuccessCode.USER_FIND_DETAIL_SUCCESS, findUserDetailResDTO);
    }

    @Operation(summary = "부서 목록 조회")
    @GetMapping("/dept/list")
    public ResponseEntity<ApiResponse<List<DeptResDTO>>> findDeptList() {

        List<DeptResDTO> deptResDTOS = userQueryService.findDeptList();

        return ResponseUtil.successResponse(SuccessCode.DEPT_READ_SUCCESS, deptResDTOS);
    }

    @Operation(summary = "부서 이름 조회")
    @GetMapping("/dept")
    public ResponseEntity<ApiResponse<DeptResDTO>> findDeptName(@RequestParam String deptCode) {

        DeptResDTO deptResDTO = userQueryService.findDeptName(deptCode);

        return ResponseUtil.successResponse(SuccessCode.DEPT_READ_SUCCESS, deptResDTO);
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
