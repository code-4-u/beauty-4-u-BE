package com.beauty4u.backend.user.query.service;

import com.beauty4u.backend.common.exception.CustomException;
import com.beauty4u.backend.common.exception.ErrorCode;
import com.beauty4u.backend.user.query.dto.FindUserDetailResDTO;
import com.beauty4u.backend.user.query.dto.UserListResDTO;
import com.beauty4u.backend.user.query.mapper.UserQueryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserQueryService {

    private final UserQueryMapper userQueryMapper;

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
}
