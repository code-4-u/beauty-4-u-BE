package com.beauty4u.backend.user.query.service;

import com.beauty4u.backend.common.exception.CustomException;
import com.beauty4u.backend.common.exception.ErrorCode;
import com.beauty4u.backend.user.query.dto.*;
import com.beauty4u.backend.user.query.mapper.DeptQueryMapper;
import com.beauty4u.backend.user.query.mapper.JobQueryMapper;
import com.beauty4u.backend.user.query.mapper.RoleQueryMapper;
import com.beauty4u.backend.user.query.mapper.UserQueryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserQueryService {

    private final UserQueryMapper userQueryMapper;
    private final DeptQueryMapper deptQueryMapper;
    private final JobQueryMapper jobQueryMapper;
    private final RoleQueryMapper roleQueryMapper;

    @Transactional(readOnly = true)
    public UserListResDTO findUserList(Long page, Long count, String search) {

        long offset = (page - 1) * count;

        System.out.println("offset = " + offset);

        UserListResDTO userListResDTO = null;

        try {
            // 회원 목록 찾기
            List<UserListDTO> userListDTO = userQueryMapper.findUserList(offset, count, search);

            userListResDTO = new UserListResDTO();
            userListResDTO.setContent(userListDTO);

            // 전체 회원 수 구하기
            Long totalUserCount = userQueryMapper.findUserListCount(search);
            userListResDTO.setTotalElements(totalUserCount);
        } catch (Exception e) {
            throw new CustomException(ErrorCode.NOT_FOUND_USER_LIST);
        }


        return userListResDTO;
    }

    @Transactional(readOnly = true)
    public FindUserDetailResDTO findUserDetail(String loginUserCode) {

        FindUserDetailResDTO findUserDetailResDTO = userQueryMapper.findUserDetail(loginUserCode);

        if (findUserDetailResDTO == null) {
            throw new CustomException(ErrorCode.NOT_FOUND_USER);
        }

        return findUserDetailResDTO;
    }

    @Transactional(readOnly = true)
    public List<DeptResDTO> findDeptList() {

        return deptQueryMapper.findDeptList();
    }

    @Transactional(readOnly = true)
    public DeptResDTO findDeptName(String deptCode) {
        return deptQueryMapper.findDeptName(deptCode);
    }

    @Transactional(readOnly = true)
    public List<JobResDTO> findJobList() {

        return jobQueryMapper.findJobList();
    }

    @Transactional(readOnly = true)
    public List<RoleResDTO> findRoleList() {

        return roleQueryMapper.findRoleList();
    }
}
