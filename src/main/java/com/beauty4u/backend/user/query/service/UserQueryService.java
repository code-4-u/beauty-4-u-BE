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
    public List<UserListResDTO> findUserList(Long page, Long count) {

        long offset = (page - 1) * count;

        return userQueryMapper.findUserList(offset, count);
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
